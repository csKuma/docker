package com.erp.autenticador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class AutenticadorApplication {
	private static final Logger logger = LoggerFactory.getLogger(AutenticadorApplication.class);


	public static void main(String[] args) {
		logger.info("Startando o projeto");
		SpringApplication.run(AutenticadorApplication.class, args);
	}

}
