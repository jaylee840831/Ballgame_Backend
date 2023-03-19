package com.example.ballgame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ballgame.dto.MessageDto;
import com.example.ballgame.dto.ResponseDto;
import com.example.ballgame.service.MessageService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class MessageController {

	@Autowired
	MessageService messageService;

	//websocket 傳輸聊天訊息節點
	@MessageMapping("/message")
	@SendTo("/topic/return-to")
	public MessageDto getContent(@RequestBody MessageDto dto) {

		try {
			
			// 儲存聊天訊息
			messageService.saveMessage(dto);
			Thread.sleep(2000);
			
		} catch (Exception e) {
			
			dto.setResponse(new ResponseDto(0, e.toString()));
			
		}

		return dto;
	}
}
