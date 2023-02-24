package com.example.ballgame.dto;

import java.util.Date;

public class MessageDto {
	private String groupUid;
	private String name;
	private String content;
	private Date date;
	
	public MessageDto(String groupUid, String name, String content, Date date) {
		this.groupUid = groupUid;
		this.name = name;
		this.content = content;
		this.date = date;
	}

	public String getGroupUid() {
		return groupUid;
	}

	public void setGroupUid(String groupUid) {
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
}
