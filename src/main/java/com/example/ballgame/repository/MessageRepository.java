package com.example.ballgame.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ballgame.dao.MessageDao;

@Repository
public interface MessageRepository extends JpaRepository<MessageDao, Long> {
	
	@Query(value = "select * from chat_room cr where cr.ball_game_chat_id = ?1 order by cr.date", nativeQuery = true)
	List<MessageDao>findMessageByBallGameChatId(Long id);
	
	@Query(value = "truncate chat_room restart identity", nativeQuery = true)
	void idReset();
}
