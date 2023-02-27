package com.example.ballgame.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ballgame.dao.BallGameDao;
import com.example.ballgame.dto.BallGameDto;
import com.example.ballgame.dto.ResponseDto;
import com.example.ballgame.repository.BallGameRepository;

import lombok.experimental.var;

@Service
public class BallGameService {

	@Autowired
	BallGameRepository ballGameRepository;

	@Autowired
	CommonService commonService;
	
	public Long ballGameCount() {
		return ballGameRepository.count();
	}

	public List<BallGameDto> getAllGames() {

		List<BallGameDao> daos = ballGameRepository.findAll();
		List<BallGameDto> dtos = new ArrayList<>();

		for (BallGameDao dao : daos) {

			dtos.add(new BallGameDto(dao.getId(), dao.getSponsor(), dao.getGameName(), dao.getCourtName(),
					dao.getStartDate(), dao.getEndDate(), dao.getNote(), new ResponseDto(1, "查詢成功")));
		}

		return dtos;
	}

	public BallGameDto getGame(Long id) {

		BallGameDao dao = ballGameRepository.findBallGame(id);

		if (dao.getSponsor().equals("")) {
			return new BallGameDto(-1L, "", "", "",
					null, null, "", new ResponseDto(0, "查無此資料"));
		}

		return new BallGameDto(dao.getId(), dao.getSponsor(), dao.getGameName(), dao.getCourtName(),
				dao.getStartDate(), dao.getEndDate(), dao.getNote(), new ResponseDto(1, "查詢成功"));
	}

	public BallGameDto addGame(BallGameDto dto) {

		BallGameDao dao = new BallGameDao();
		dao.setSponsor(dto.getSponsor());
		dao.setGameName(dto.getGameName());
		dao.setCourtName(dto.getCourtName());
		dao.setStartDate(commonService.convertDateToLocal("UTC+8", dto.getStartDate()));
		dao.setEndDate(commonService.convertDateToLocal("UTC+8", dto.getEndDate()));
		dao.setNote(dto.getNote());

		var saveBallGame = ballGameRepository.save(dao);

		if (saveBallGame.getSponsor().equals("")) {
			dto.setResponse(new ResponseDto(0, "新增失敗"));
		} else {
			dto.setId(saveBallGame.getId());
			dto.setResponse(new ResponseDto(1, "新增成功"));
		}

		return dto;
	}
}
