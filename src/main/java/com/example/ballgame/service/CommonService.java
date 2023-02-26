package com.example.ballgame.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class CommonService {
	
	private LocalTime lt;

	public Date getCurrentTime(String zoneId) {
		
		lt = LocalTime.now();
		Instant instant = lt.atDate(LocalDate.now()).
		        atZone(ZoneId.of(zoneId)).toInstant();
		Date date = Date.from(instant);

		return date;
	}
}
