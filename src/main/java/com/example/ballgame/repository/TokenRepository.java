package com.example.ballgame.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.ballgame.security.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {

	@Query(value = 
				  "select t from Token t inner join UserDao u "+
			      "on t.user.id = u.id "+
			      "where u.id = :id and (t.expired = false or t.revoked = false) ")
	List<Token> findAllValidTokenByUser(Long id);
	
	Optional<Token> findByToken(String token);
	
	Optional<Token> findByUserId(Long userId);
  
	@Transactional
	@Modifying
	@Query(value="UPDATE Token t SET t.token= :token, t.updateDate= :updateDate WHERE t.user.id= :userId")
	void updateByUserId(@Param("userId")Long userId,@Param("token")String token,@Param("updateDate")Date updateDate);
}
