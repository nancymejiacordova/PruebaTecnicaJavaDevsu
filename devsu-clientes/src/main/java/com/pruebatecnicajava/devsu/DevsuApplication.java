package com.pruebatecnicajava.devsu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
/*import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient*/
@EnableConfigurationProperties
@SpringBootApplication
public class DevsuApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevsuApplication.class, args);
	}

}
