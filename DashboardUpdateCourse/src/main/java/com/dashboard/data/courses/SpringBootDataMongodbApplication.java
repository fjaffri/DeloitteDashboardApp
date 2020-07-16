package com.dashboard.data.courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringBootDataMongodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDataMongodbApplication.class, args);
	}

}
