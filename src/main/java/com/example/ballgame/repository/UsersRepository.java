package com.example.ballgame.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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
}
