package com.pruebatecnicajava.devsu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class DevsuMovimientosApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevsuMovimientosApplication.class, args);
	}

}
