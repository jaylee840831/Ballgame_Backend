package com.example.ballgame.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ballgame.dao.UserDao;
import com.example.ballgame.dto.AuthenticationRequest;
import com.example.ballgame.dto.AuthenticationResponse;
import com.example.ballgame.dto.RegisterRequest;
import com.example.ballgame.repository.TokenRepository;
import com.example.ballgame.repository.UsersRepository;
import com.example.ballgame.security.Role;
import com.example.ballgame.security.Token;
import com.example.ballgame.security.TokenType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	@Autowired
  private UsersRepository repository;
	@Autowired
  private TokenRepository tokenRepository;
	@Autowired
  private PasswordEncoder passwordEncoder;
	@Autowired
  private JwtService jwtService;
	@Autowired
  private AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {
//	UserDao user = UserDao.builder()
//        .name(request.getName())
//        .email(request.getEmail())
//        .password(passwordEncoder.encode(request.getPassword()))
//        .role(Role.USER)
//        .build();
	
	UserDao user = new UserDao();
	user.setName(request.getName());
	user.setEmail(request.getEmail());
	user.setPassword(passwordEncoder.encode(request.getPassword()));
	user.setRole(Role.USER);
	
    var savedUser = repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    saveUserToken(savedUser, jwtToken);
    
    AuthenticationResponse response = new AuthenticationResponse();
    response.setJwt(jwtToken);
    response.setStatus(1);
    response.setMessage("");
    
    return response;
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    var user = repository.findByEmail(request.getEmail())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);

    AuthenticationResponse response = new AuthenticationResponse();
    response.setJwt(jwtToken);
    response.setStatus(1);
    response.setMessage("");
    
    return response;
  }

  private void saveUserToken(UserDao user, String jwtToken) {
//	Token token = Token.builder()
//        .user(user)
//        .token(jwtToken)
//        .tokenType(TokenType.BEARER)
//        .expired(false)
//        .revoked(false)
//        .build();
	
	Token token = new Token();
	token.setUser(user);
	token.setToken(jwtToken);
	token.setTokenType(TokenType.BEARER);
	token.setExpired(false);
	token.setRevoked(false);
	
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(UserDao user) {
	List<Token> validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }
}