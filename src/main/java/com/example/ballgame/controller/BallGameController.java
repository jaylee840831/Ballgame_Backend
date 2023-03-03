package com.example.ballgame.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ballgame.dto.BallGameDto;
import com.example.ballgame.dto.MarkGameDto;
import com.example.ballgame.dto.ResponseDto;
import com.example.ballgame.service.BallGameService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/ballgame")
public class BallGameController {
	
	@Autowired
	private BallGameService ballGameService;

	@PostMapping("/all")
	public ResponseEntity<List<BallGameDto>> allGame(@RequestBody MarkGameDto dto){
		return ResponseEntity.ok(ballGameService.getAllGames(dto));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BallGameDto> getGame(@PathVariable("id") Long id){
		return ResponseEntity.ok(ballGameService.getGame(id));
	}
	
	@PostMapping("/new")
	public ResponseEntity<BallGameDto> addGame(@RequestBody BallGameDto ballGameDto){
		
		if(ballGameService.ballGameCount() >= 30) {
			return ResponseEntity.ok(new BallGameDto(-1L, "", "", "",
					null, null, "", false, new ResponseDto(0, "球局已達上限數量")));
		}
		
		return ResponseEntity.ok(ballGameService.addGame(ballGameDto));
	}
	
	//user儲存的球局
	@PostMapping("/mark/user")
	public ResponseEntity<List<BallGameDto>> allBallGameMark(@RequestBody MarkGameDto dto){
		return ResponseEntity.ok(ballGameService.allMarkGame(dto));
	}
	
	//儲存球局
	@PostMapping("/mark")
	public ResponseEntity<MarkGameDto> ballGameMark(@RequestBody MarkGameDto dto) {
		return ResponseEntity.ok(ballGameService.markGame(dto));
	}
	
	//取消儲存球局
	@PostMapping("/mark/delete")
	public ResponseEntity<MarkGameDto> deleteBallGameMark(@RequestBody MarkGameDto dto) {
		return ResponseEntity.ok(ballGameService.deleteMarkGame(dto));
	}
}
