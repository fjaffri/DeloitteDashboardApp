package com.deloitte.dashboard.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class DashboardApiGatewayApplication {
//main
	public static void main(String[] args) {
		SpringApplication.run(DashboardApiGatewayApplication.class, args);
	}

}
