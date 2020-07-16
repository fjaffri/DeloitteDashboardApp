package com.dashboard.data.courses.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.util.IOUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.dashboard.data.courses.controller.CourseUploadController;
import com.dashboard.data.courses.model.Course;
import com.dashboard.data.courses.repository.UserDao;
import com.dashboard.data.courses.services.CourseUploadService;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CousereUploadServiceTest {

	@InjectMocks
	CourseUploadController courseUploadController;

	@Mock
	CourseUploadService courseUploadService;

	@Mock
	UserDao useDao;


	@Test
	public void testUserDao() throws IOException {
		Course c1 = new Course();
		c1.setUserEmail("fjay@deloitte.com");
		Course c2 = new Course();
		c2.setUserEmail("vjay@deloitte.com");
		List<Course> courses = new ArrayList<>();
		courses.add(c1);
		courses.add(c2);

		File file = new File(getClass().getClassLoader().getResource("Udemy_data.csv").getFile());
		FileInputStream input = new FileInputStream(file);
		MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain",
				IOUtils.toByteArray(input));

		when(courseUploadService.updateCourses(multipartFile)).thenReturn(courses);
		assertEquals(2, courses.size());
	}

	@Test
	public void testUploadCourseTakenByUsers() throws IOException {
		Course c1 = new Course();
		c1.setUserEmail("fjay@deloitte.com");
		Course c2 = new Course();
		c2.setUserEmail("vjay@deloitte.com");
		List<Course> courses = new ArrayList<>();
		courses.add(c1);
		courses.add(c2);

		doNothing().when(useDao).updateDB(courses);
		assertEquals(2, courses.size());
	}
}