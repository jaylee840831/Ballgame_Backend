package com.example.ballgame.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ballgame.dao.UserDao;

@Repository
public interface UsersRepository extends JpaRepository<UserDao,Long>{

	Optional<UserDao> findByEmail(String email);
}
