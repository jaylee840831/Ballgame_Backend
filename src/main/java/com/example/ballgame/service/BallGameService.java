package com.example.ballgame.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ballgame.dao.BallGameDao;
import com.example.ballgame.dao.MarkGameDao;
import com.example.ballgame.dto.BallGameDto;
import com.example.ballgame.dto.MarkGameDto;
import com.example.ballgame.dto.ResponseDto;
import com.example.ballgame.repository.BallGameRepository;
import com.example.ballgame.repository.UsersRepository;

import lombok.experimental.var;

@Service
public class BallGameService {

	@Autowired
	BallGameRepository ballGameRepository;

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	CommonService commonService;

	public Long ballGameCount() {
		return ballGameRepository.count();
	}

	public List<BallGameDto> getAllGames(MarkGameDto dto) {

		List<BallGameDao> daos = ballGameRepository.findAll();
		List<BallGameDto> response = new ArrayList<>();

		List<Object> markDaos = new ArrayList<>();
		markDaos = ballGameRepository.getAllMarkGames(dto.getEmail());

		if (markDaos.size() > 0) {

			for (BallGameDao dao : daos) {

				boolean isMark = false;

				for (Object markDao : markDaos) {

					HashMap<String, Long> userGameId = extractObject(markDao.toString());

					if (dao.getId().equals(userGameId.get("gameId"))) {

						isMark = true;
						break;

					} else {

						isMark = false;

					}
				}

				if (isMark) {

					response.add(new BallGameDto(dao.getId(), dao.getSponsor(), dao.getGameName(), dao.getCourtName(),
							dao.getStartDate(), dao.getEndDate(), dao.getNote(), true, new ResponseDto(1, "查詢成功")));

				} else {

					response.add(new BallGameDto(dao.getId(), dao.getSponsor(), dao.getGameName(), dao.getCourtName(),
							dao.getStartDate(), dao.getEndDate(), dao.getNote(), false, new ResponseDto(1, "查詢成功")));

				}
			}

		} else {

			for (BallGameDao dao : daos) {

				response.add(new BallGameDto(dao.getId(), dao.getSponsor(), dao.getGameName(), dao.getCourtName(),
						dao.getStartDate(), dao.getEndDate(), dao.getNote(), false, new ResponseDto(1, "查詢成功")));
			}

		}

		return response;
	}

	public BallGameDto getGame(Long id) {

		BallGameDao dao = ballGameRepository.findBallGame(id);

		if (dao.getSponsor().equals("")) {
			return new BallGameDto(-1L, "", "", "", null, null, "", false, new ResponseDto(0, "查無此資料"));
		}

		return new BallGameDto(dao.getId(), dao.getSponsor(), dao.getGameName(), dao.getCourtName(), dao.getStartDate(),
				dao.getEndDate(), dao.getNote(), false, new ResponseDto(1, "查詢成功"));
	}

	public BallGameDto addGame(BallGameDto dto) {

		if (dto.getStartDate().after(dto.getEndDate())) {
			dto.setResponse(new ResponseDto(0, "新增失敗，開始時間不可大於結束時間"));
			return dto;
		}

		BallGameDao dao = new BallGameDao();
		dao.setSponsor(dto.getSponsor());
		dao.setGameName(dto.getGameName());
		dao.setCourtName(dto.getCourtName());
		dao.setStartDate(dto.getStartDate());
		dao.setEndDate(dto.getEndDate());
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

//	public List<MarkGameDto> allMarkGame(MarkGameDto dto) {
//		
//		List<MarkGameDto> dtos = new ArrayList<>();
//		List<HashMap<String, Object>> daos = new ArrayList<>();
//		
//		daos = ballGameRepository.getAllMarkGames(dto.getEmail());
//		
//		if(daos.size() > 0) {
//			
//		}
//		
//		return dtos;
//	}

	public MarkGameDto markGame(MarkGameDto dto) {

		try {
			usersRepository.markGame(dto.getEmail(), dto.getGameId());
			dto.setResponse(new ResponseDto(1, "儲存成功"));
		} catch (Exception e) {
			dto.setResponse(new ResponseDto(0, "儲存失敗"));
		}

		return dto;
	}

	public MarkGameDto deleteMarkGame(MarkGameDto dto) {

		try {
			usersRepository.deleteMarkGame(dto.getEmail(), dto.getGameId());
			dto.setResponse(new ResponseDto(1, "儲存成功"));
		} catch (Exception e) {
			dto.setResponse(new ResponseDto(0, "儲存失敗"));
		}

		return dto;
	}

	public HashMap<String, Long> extractObject(String str) {

		HashMap<String, Long> map = new HashMap<String, Long>();
		str = str.replaceAll("\\(", "");
		str = str.replaceAll("\\)", "");
		String[] str2 = str.split(",");
		map.put("gameId", Long.parseLong(str2[1]));

		return map;
	}
}
