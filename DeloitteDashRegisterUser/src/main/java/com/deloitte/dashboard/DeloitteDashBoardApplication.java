package com.deloitte.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
public class DeloitteDashBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeloitteDashBoardApplication.class, args);
	}

}
