package com.example.ballgame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ballgame.dto.AuthenticationRequest;
import com.example.ballgame.dto.AuthenticationResponse;
import com.example.ballgame.dto.RegisterRequest;
import com.example.ballgame.dto.UserDto;
import com.example.ballgame.repository.UsersRepository;
import com.example.ballgame.service.AuthenticationService;





@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
	
	@Autowired
	private  AuthenticationService service;

	  @PostMapping("/register")
	  public ResponseEntity<AuthenticationResponse> register(
	      @RequestBody RegisterRequest request
	  ) {
		  if(service.userCount() >= 5) {
			 AuthenticationResponse errorResponse = new AuthenticationResponse();
			 errorResponse.setJwt("");
			 errorResponse.setMessage("註冊已達上限數量");
			 errorResponse.setStatus(0);
			 return ResponseEntity.ok(errorResponse);
		  }
		  
		  return ResponseEntity.ok(service.register(request));
	  }
	  @PostMapping("/authenticate")
	  public ResponseEntity<AuthenticationResponse> authenticate(
	      @RequestBody AuthenticationRequest request
	  ) {
	    return ResponseEntity.ok(service.authenticate(request));
	  }

}
