package com.reservasalas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ReservaSalasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservaSalasApplication.class, args);
	}

}
