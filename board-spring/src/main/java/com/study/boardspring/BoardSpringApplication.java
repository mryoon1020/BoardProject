package com.study.boardspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.study.*"})
public class BoardSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardSpringApplication.class, args);
	}

}
