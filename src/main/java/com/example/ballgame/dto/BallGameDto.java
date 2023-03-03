package com.example.ballgame.dto;

import java.util.Date;

public class BallGameDto {

	private Long id;
	private String sponsor;
	private String gameName;
	private String courtName;
	private Date startDate;
	private Date endDate;
	private String note;
	private boolean isMark = false;
	private ResponseDto response;
	
	public BallGameDto() {
	}
	
	public BallGameDto(Long id, String sponsor, String gameName, String courtName, Date startDate, Date endDate,
			String note, boolean isMark, ResponseDto response) {
		super();
		this.id = id;
		this.sponsor = sponsor;
		this.gameName = gameName;
		this.courtName = courtName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.note = note;
		this.isMark = isMark;
		this.response = response;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getCourtName() {
		return courtName;
	}

	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public ResponseDto getResponse() {
		return response;
	}

	public void setResponse(ResponseDto response) {
		this.response = response;
	}

	public boolean isMark() {
		return isMark;
	}

	public void setMark(boolean isMark) {
		this.isMark = isMark;
	}
}
