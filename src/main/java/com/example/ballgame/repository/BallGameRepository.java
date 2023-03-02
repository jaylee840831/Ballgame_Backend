package com.example.ballgame.repository;

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
}
