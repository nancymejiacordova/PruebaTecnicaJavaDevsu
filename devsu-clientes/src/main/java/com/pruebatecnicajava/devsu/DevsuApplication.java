package com.pruebatecnicajava.devsu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class DevsuApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevsuApplication.class, args);
	}

}
