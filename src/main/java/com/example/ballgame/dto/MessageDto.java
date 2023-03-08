package com.example.ballgame.dto;

import java.util.Date;

public class MessageDto {
	private Long groupUid;
	private String name;
	private String content;
	private Date date;
	private ResponseDto response;
	
	public MessageDto() {
		
	}

	public MessageDto(Long groupUid, String name, String content, Date date) {
		this.groupUid = groupUid;
		this.name = name;
		this.content = content;
		this.date = date;
	}

	public MessageDto(Long groupUid, String name, String content, Date date, ResponseDto response) {
		this.groupUid = groupUid;
		this.name = name;
		this.content = content;
		this.date = date;
		this.response = response;
	}

	public Long getGroupUid() {
		return groupUid;
	}

	public void setGroupUid(Long groupUid) {
		this.groupUid = groupUid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ResponseDto getResponse() {
		return response;
	}

	public void setResponse(ResponseDto response) {
		this.response = response;
	}

}
