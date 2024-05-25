package com.userssalas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UsersSalasApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersSalasApplication.class, args);
	}

}
