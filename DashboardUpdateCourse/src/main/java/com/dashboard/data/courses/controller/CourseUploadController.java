package com.dashboard.data.courses.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dashboard.data.courses.Exception.ItemNotFoundException;
import com.dashboard.data.courses.model.Course;
import com.dashboard.data.courses.model.User;
import com.dashboard.data.courses.repository.UserDao;
import com.dashboard.data.courses.services.CourseUploadService;

/**
 * <h1>Courses Upload Controller</h1> This controller associates registered
 * users with their courses
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
	 * @param A csv file which contains courses details attended by each registered
	 *          user
	 * @return String This returns a messege showing success status to the client.
	 */
	@PostMapping("/course")
	public String uploadCourseTakenByUsers(@RequestParam("file") MultipartFile file) throws IOException {
		logger.info("In CourseUploadController uploadCourseTakenByUsers()");
		if (!file.getOriginalFilename().endsWith("csv")) {

			throw new ItemNotFoundException("File should be .csv format");
		}
		long time = System.currentTimeMillis();

		List<Course> courses = courseUploadService.updateCourses(file);
		if (!courses.isEmpty()) {
			userDao.updateDB(courses);
			logger.info("Ending CourseUploadController uploadCourseTakenByUsers()");
			long responseTime = (System.currentTimeMillis() - time);
			System.out.println("Execution time for uploading courses associated with users "+responseTime);
			return "Courses updated with users successfully";
		} else {
			throw new ItemNotFoundException("File does not conatin data");
		}
	}

	/**
	 * This API retrieves all the participants data from MongoDb and writes to CSV file
	 *
	 * @return String This returns a messege showing success or failiure.
	 */
	@GetMapping("/data")
	public String getAllUserData() {
		logger.info("In CourseUploadController getAllUserData()");
		long time = System.currentTimeMillis();

		List<User> userData = userDao.getAllDataFromDB();
		String res = courseUploadService.writeDataAtOnce(userData);
		if (res.equals("success")) {
			long responseTime = (System.currentTimeMillis() - time);
			System.out.println("Execution time for getting participants data in CSV API "+responseTime);

			return "Data written into Participant.csv file ";

		}

		logger.info("Ending CourseUploadController getAllUserData()");
		return "Error Writing Data";

	}

}
