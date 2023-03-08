package com.example.ballgame.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.ballgame.repository.MessageRepository;

@Component
public class ScheduleTasks {
	
	@Autowired
	MessageRepository messageRepository;

	 @Scheduled(cron = "0 0 12 * * ?")
	 public void clearChatRoomAndResetId() {

		 messageRepository.idReset();
		 
	 }
}
