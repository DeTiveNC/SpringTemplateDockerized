package com.pee.dockerized.postgresql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DockerizedPostgresqlApp {
	public static void main(String[] args) {
		SpringApplication.run(DockerizedPostgresqlApp.class, args);
	}
}
