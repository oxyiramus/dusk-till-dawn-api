package com.dusktilldawn.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DuskTillDawnApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DuskTillDawnApiApplication.class, args);
	}

}
