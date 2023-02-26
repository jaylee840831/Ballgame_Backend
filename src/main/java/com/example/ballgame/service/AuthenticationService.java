package com.example.ballgame.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
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
  CommonService commonService;
  
  @Autowired
  private AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {
	
	AuthenticationResponse response = new AuthenticationResponse();  
	  
	var user = repository.findByEmail(request.getEmail());
	if(!user.isEmpty()) {
        response.setJwt("");
        response.setStatus(0);
        response.setMessage("email已被註冊");
        return response;
	}
	  
	UserDao userDao = new UserDao();
	userDao.setName(request.getName());
	userDao.setEmail(request.getEmail());
	userDao.setPassword(passwordEncoder.encode(request.getPassword()));//使用者密碼加密
	userDao.setRole(Role.USER);
	
    var savedUser = repository.save(userDao);
//    var jwtToken = jwtService.generateToken(user);
//    saveUserToken(savedUser, jwtToken);
    
    if(!savedUser.getEmail().equals("")) {
    	response.setName(savedUser.getName());
        response.setJwt("");
        response.setStatus(1);
        response.setMessage("註冊成功");
    }
    else {
        response.setJwt("");
        response.setStatus(0);
        response.setMessage("註冊失敗");
    }
    
    return response;
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
	  
	AuthenticationResponse response = new AuthenticationResponse(); 
	  
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    
    var user = repository.findByEmail(request.getEmail())
        .orElseThrow();
    
    var jwtToken = jwtService.generateToken(user);
//    revokeAllUserTokens(user);
    
    var findToken = tokenRepository.findByUserId(user.getId());
    if(!findToken.isEmpty()) {
    	updateUserToken(user.getId(), jwtToken);
    }else {
    	saveUserToken(user, jwtToken);
    }

    response.setName(user.getName());
    response.setJwt(jwtToken);
    response.setStatus(1);
    response.setMessage("登入成功");
    
    return response;
  }
  
  public Long userAmount() {
	  return repository.count();
  }
  
  private void updateUserToken(Long userId, String jwtToken) {
	
    tokenRepository.updateByUserId(userId, jwtToken, commonService.getCurrentTime("UTC+8"));
	  
  }

  private void saveUserToken(UserDao user, String jwtToken) {
	
	Token token = new Token();
	token.setUser(user);
	token.setToken(jwtToken);
	token.setTokenType(TokenType.BEARER);
	token.setExpired(false);
	token.setRevoked(false);
	token.setCreateDate(commonService.getCurrentTime("UTC+8"));
	token.setUpdateDate(commonService.getCurrentTime("UTC+8"));
	
    tokenRepository.save(token);
  }

//  private void revokeAllUserTokens(UserDao user) {
//	List<Token> validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
//    if (validUserTokens.isEmpty())
//      return;
//    validUserTokens.forEach(token -> {
//      token.setExpired(true);
//      token.setRevoked(true);
//    });
//    tokenRepository.saveAll(validUserTokens);
//  }
  
}