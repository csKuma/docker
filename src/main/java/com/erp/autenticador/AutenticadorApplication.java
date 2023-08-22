package com.erp.autenticador;

import com.erp.autenticador.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
public class AutenticadorApplication {
	private static final Logger logger = LoggerFactory.getLogger(AutenticadorApplication.class);


	public static void main(String[] args) {
		logger.info("Startando o projeto");
		SpringApplication.run(AutenticadorApplication.class, args);
	}

}
