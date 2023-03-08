package com.example.ballgame.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.ballgame.dao.BallGameDao;

@Repository
public interface BallGameRepository extends JpaRepository<BallGameDao, Long>{

	@Query(value = "select b from BallGameDao b where b.id= :id")
	BallGameDao findBallGame(Long id);
	
	@Transactional
	@Modifying
	@Query(value = "update BallGameDao b set b.sponsor = :name where b.sponsor = :name2")
	void updateSponsorByName(String name, String name2);
	
	@Query(value = "select ubg from _user_ball_game ubg where ubg.user_id = (select u.id from _user u where u.email = ?1)", nativeQuery = true)
	List<Object> getAllMarkGames(String email);
	
	@Query(value = "select * from ball_game b where b.id = ?1", nativeQuery = true)
	BallGameDao findByid(Long id);
}
