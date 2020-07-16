package com.deloitte.dashboard.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class DashboardEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DashboardEurekaServerApplication.class, args);
	}

}
