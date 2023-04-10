package com.example.Restaurante.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.example.Restaurante"})
@EntityScan("com.example.Restaurante")
@EnableJpaRepositories("com.example.Restaurante")
public class RestauranteApplication {

	private static final Logger LOG = LoggerFactory.getLogger(RestauranteApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(RestauranteApplication.class, args);
		LOG.info("\n ----------------------------Inicio de la aplicacion----------------------------\n \t");
	}

}
