package com.example.ballgame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.ballgame.dao.UserInfoDao;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfoDao, Long> {

	@Transactional
	@Modifying
	@Query(value = "update UserInfoDao ui set ui.height = :height, ui.weight = :weight, ui.name = :name, ui.sex = :sex, ui.position = :position, ui.image = :image "
			+ "where ui.id = (select ui.id from UserInfoDao ui, UserDao u where u.email = :email and ui.user.id = u.id)")
	void updateUserInfo(String name, String height, String weight, String sex, String position, byte[] image,
			String email);

	@Transactional
	@Modifying
	@Query(value = "update UserInfoDao ui set ui.height = :height, ui.weight = :weight, ui.name = :name, ui.sex = :sex, ui.position = :position "
			+ "where ui.id = (select ui.id from UserInfoDao ui, UserDao u where u.email = :email and ui.user.id = u.id)")
	void updateUserInfoWithoutImage(String name, String height, String weight, String sex, String position,
			String email);
}
