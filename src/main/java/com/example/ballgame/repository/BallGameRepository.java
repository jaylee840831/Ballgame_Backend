package com.example.ballgame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ballgame.dao.BallGameDao;

@Repository
public interface BallGameRepository extends JpaRepository<BallGameDao, Long>{

	@Query(value = "select b from BallGameDao b where b.id= :id")
	BallGameDao findBallGame(Long id);
}
