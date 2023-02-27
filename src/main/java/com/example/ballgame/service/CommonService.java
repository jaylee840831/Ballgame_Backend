package com.example.ballgame.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class CommonService {

	private LocalTime lt;

	public Date getCurrentTime(String zoneId) {

		lt = LocalTime.now();
		Instant instant = lt.atDate(LocalDate.now()).atZone(ZoneId.of(zoneId)).toInstant();
		Date date = Date.from(instant);

		return date;
	}

	public Date convertDateToLocal(String zoneId, Date d) {

		LocalTime localTime = LocalDateTime.ofInstant(d.toInstant(), ZoneId.of(zoneId)).toLocalTime();

		LocalDate localDate = d.toInstant().atZone(ZoneId.of(zoneId)).toLocalDate();
		
		Instant instant = localTime.atDate(localDate).atZone(ZoneId.of(zoneId)).toInstant();
		Date date = Date.from(instant);

		return date;
	}
}
