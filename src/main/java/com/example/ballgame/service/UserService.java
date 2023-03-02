package com.example.ballgame.service;

import java.util.HashMap;
import java.util.Map;

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
		
		System.out.println(dto.getpName());

		try {

			if (dto.getImage() != null) {

				userInfoRepository.updateUserInfo(dto.getName(), dto.getHeight(), dto.getWeight(), dto.getSex(),
						dto.getPosition(), dto.getImage(), dto.getEmail());

			} else {

				userInfoRepository.updateUserInfoWithoutImage(dto.getName(), dto.getHeight(), dto.getWeight(), dto.getSex(),
						dto.getPosition(), dto.getEmail());

			}

			usersRepository.updateNameByEmail(dto.getName(), dto.getEmail());
			ballGameRepository.updateSponsorByName(dto.getName(), dto.getpName());

			dto.setResponse(new ResponseDto(1, "編輯成功"));

		} catch (Exception e) {

			dto.setResponse(new ResponseDto(0, "編輯失敗," + e));

		}

		return dto;
	}
}
