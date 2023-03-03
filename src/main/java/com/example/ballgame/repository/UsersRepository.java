package com.example.ballgame.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.ballgame.dao.UserDao;

@Repository
public interface UsersRepository extends JpaRepository<UserDao, Long> {

	Optional<UserDao> findByEmail(String email);

	@Transactional
	@Modifying
	@Query(value = "update UserDao u set u.name = :name where u.email = :email")
	void updateNameByEmail(String name, String email);

	@Query(value = "insert into _user_ball_game values((select u.id from _user u where u.email = ?1), ?2)", nativeQuery = true)
	void markGame(String email, Long gameId);
	
	@Transactional
	@Modifying
	@Query(value = "delete from _user_ball_game ubg where ubg.user_id = (select u.id from _user u where u.email = ?1) and ubg.ball_game_id = ?2", nativeQuery = true)
	void deleteMarkGame(String email, Long gameId);
}
