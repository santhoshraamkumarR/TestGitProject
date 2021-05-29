package com.springmongo.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableConfigServer
@SpringBootApplication
//@EnableCircuitBreaker
//@EnableEurekaClient
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

}
