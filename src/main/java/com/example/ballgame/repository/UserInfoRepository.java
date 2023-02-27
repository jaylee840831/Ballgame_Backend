package com.example.ballgame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ballgame.dao.UserInfoDao;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfoDao, Long>{

	
}
