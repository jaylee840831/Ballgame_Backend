package com.example.ballgame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BallgameBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BallgameBackendApplication.class, args);
	}

}
