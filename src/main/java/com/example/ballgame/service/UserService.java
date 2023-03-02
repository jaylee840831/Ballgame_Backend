package com.example.ballgame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ballgame.dao.UserInfoDao;
import com.example.ballgame.dto.ResponseDto;
import com.example.ballgame.dto.UserInfoDto;
import com.example.ballgame.repository.BallGameRepository;
import com.example.ballgame.repository.UserInfoRepository;
import com.example.ballgame.repository.UsersRepository;

@Service
public class UserService {

	@Autowired
	UserInfoRepository userInfoRepository;

	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	BallGameRepository ballGameRepository;

	public UserInfoDto editUserInfo(UserInfoDto dto) {

		try {

//			if (dto.getImage() != null) {
//
//				userInfoRepository.updateUserInfo(dto.getName(), dto.getHeight(), dto.getWeight(), dto.getSex(),
//						dto.getPosition(), dto.getImage(), dto.getEmail());
//
//			} else {
//
//				userInfoRepository.updateUserInfoWithoutImage(dto.getName(), dto.getHeight(), dto.getWeight(), dto.getSex(),
//						dto.getPosition(), dto.getEmail());
//
//			}
			
			userInfoRepository.updateUserInfoWithoutImage(dto.getName(), dto.getHeight(), dto.getWeight(), dto.getSex(),
					dto.getPosition(), dto.getEmail());

			usersRepository.updateNameByEmail(dto.getName(), dto.getEmail());
			ballGameRepository.updateSponsorByName(dto.getName(), dto.getpName());

			dto.setResponse(new ResponseDto(1, "編輯成功"));

		} catch (Exception e) {

			dto.setResponse(new ResponseDto(0, "編輯失敗," + e));

		}

		return dto;
	}
	
	public UserInfoDto getUserInfo(String email) {
		
		UserInfoDto dto = new UserInfoDto();
		UserInfoDao dao = userInfoRepository.findByEmail(email);
		
		if(!dao.getName().equals("")) {
			
			dto.setEmail(email);
			dto.setName(dao.getName());
			dto.setSex(dao.getSex());
			dto.setHeight(dao.getHeight());
			dto.setWeight(dao.getWeight());
			dto.setPosition(dao.getPosition());
//			dto.setImage(dao.getImage());
			
			dto.setResponse(new ResponseDto(1, "查詢成功"));
			
		}else {
			
			dto.setResponse(new ResponseDto(0, "查詢失敗"));
			
		}
		
		return dto;
	}
}
