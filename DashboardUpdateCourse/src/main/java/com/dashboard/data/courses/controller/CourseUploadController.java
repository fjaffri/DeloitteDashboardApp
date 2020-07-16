package com.dashboard.data.courses.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dashboard.data.courses.model.Course;
import com.dashboard.data.courses.repository.UserDao;
import com.dashboard.data.courses.services.CourseUploadService;

/**
 * <h1>Courses Upload Controller</h1>
 * This controller associates registered users
 * with their courses
 * 
 * @author fjaffri
 * @version 1.0
 * @since 2020-07-15
 * 
 */
@EnableEurekaClient
@RestController

public class CourseUploadController {

	@Autowired
	CourseUploadService courseUploadService;

	@Autowired
	UserDao userDao;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * This API associates users with there respected courses
	 * 
	 * @param A csv file which contains courses details attended by each registered user
	 * @return String This returns a messege showing success status to the client.
	 */
	@PostMapping("/course")
	public String uploadCourseTakenByUsers(@RequestParam("file") MultipartFile file) throws IOException {
		logger.info("In CourseUploadController uploadCourseTakenByUsers()");

		List<Course> courses = courseUploadService.updateCourses(file);
		if (courses != null) {
			userDao.updateDB(courses);
			logger.info("Ending CourseUploadController uploadCourseTakenByUsers()");

		return "Courses updated with users successfully";
		}
		return "Error while parsing CSV file";
	}
}
