package com.example.ballgame.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/demo")
public class demoController {

	@GetMapping
	public ResponseEntity<String>hello(){
		return ResponseEntity.ok("hello from secured endpoint");
	}
}
