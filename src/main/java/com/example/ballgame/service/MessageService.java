package com.example.ballgame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ballgame.dao.BallGameDao;
import com.example.ballgame.dao.MessageDao;
import com.example.ballgame.dto.MessageDto;
import com.example.ballgame.dto.ResponseDto;
import com.example.ballgame.repository.BallGameRepository;
import com.example.ballgame.repository.MessageRepository;

@Service
public class MessageService {

	@Autowired
	MessageRepository messageRepository;
	
	@Autowired
	BallGameRepository ballGameRepository;

	public MessageDto saveMessage(MessageDto dto) {
		
		BallGameDao d = ballGameRepository.findByid(dto.getGroupUid());

		MessageDao dao = new MessageDao();
		dao.setBallGameId(d);
		dao.setName(dto.getName());
		dao.setContent(dto.getContent());
		dao.setDate(dto.getDate());
		dao = messageRepository.save(dao);
		
		if(dao.getBallGameId() != null){
			
			dto.setResponse(new ResponseDto(1,"儲存聊天訊息成功"));
			
		}else {
			
			dto.setResponse(new ResponseDto(0,"儲存聊天訊息失敗"));
			
		}

		return dto;

	}
}
