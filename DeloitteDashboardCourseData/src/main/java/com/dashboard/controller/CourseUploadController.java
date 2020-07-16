package com.dashboard.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dashboard.course.UserDao;
import com.dashboard.model.Course;
import com.dashboard.service.CourseUploadService;

@RestController
public class CourseUploadController {

	@Autowired
	CourseUploadService courseUploadService;

	UserDao userDao = new UserDao();

	
	@PostMapping("/upload/course")
	public String uploadCourseTakenByUsers(@RequestParam("file") MultipartFile file) throws IOException {
		List<Course> courses = courseUploadService.updateCourses(file);
		userDao.updateDB(courses);
		return "All rows inserted successfully in DB";
	}
}
