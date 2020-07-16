package com.dashboard.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan({"com.dashboard.controller","com.dashboard.course","com.dashboard.service"})
@SpringBootApplication
public class DeloitteDashboardCourseDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeloitteDashboardCourseDataApplication.class, args);
	}

}
