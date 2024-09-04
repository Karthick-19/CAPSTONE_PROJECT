package com.learn.afternoon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AfternoonApplication {

	public static void main(String[] args) {
		SpringApplication.run(AfternoonApplication.class, args);
	}

}
