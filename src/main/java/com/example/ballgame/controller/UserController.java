package com.example.ballgame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ballgame.dto.UserInfoDto;
import com.example.ballgame.service.UserService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/info/edit")
	public ResponseEntity<UserInfoDto> editUserInfo(@RequestBody UserInfoDto dto) {
		return ResponseEntity.ok(userService.editUserInfo(dto));
	}
}
