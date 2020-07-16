package com.dashboard.data.courses.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dashboard.data.courses.model.Course;

/**
 * <h1>Courses Upload Service for processing CSV DATA</h1> This Service class
 * iterates through each and every row in CSV and map it with Course object
 * 
 * @author fjaffri
 * @version 1.0
 * @since 2020-07-15
 * 
 */
@Service
public class CourseUploadService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * This method iterates through csv file and maps each row to Course Object
	 * 
	 * @param A csv file which contains courses details attended by each registered user.
	 * @return List<Course> This returns list of courses
	 */
	public List<Course> updateCourses(MultipartFile file) throws IOException {
		logger.info("In CourseUploadService updateCourses()");

		byte[] byteData = file.getBytes();
		ByteArrayInputStream is = new ByteArrayInputStream(byteData);
		InputStreamReader reader = new InputStreamReader(is);
		CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
		List<Course> courses = new ArrayList<>();

		for (CSVRecord csvRecord : csvParser) {
			if (csvRecord.getRecordNumber() != 1) {
				Course course = new Course();

				// Accessing Values by Column Index
				course.setPercentMarked(csvRecord.get(0));
				course.setCourseCatagory(csvRecord.get(1));
				course.setCourseId(csvRecord.get(2));
				course.setCourseTitle(csvRecord.get(3));
				course.setDateCompleted(csvRecord.get(4));
				course.setDateEnrolled(csvRecord.get(5));
				course.setDateFirstCompleted(csvRecord.get(6));
				course.setDateLastAccesed(csvRecord.get(7));
				course.setDateStarted(csvRecord.get(8));
				course.setUserFirstName(csvRecord.get(9));
				course.setUserLastName(csvRecord.get(10));
				course.setUserEmail(csvRecord.get(11));
				course.setYearOfRangeDate(csvRecord.get(12));
				course.setCourseDurationCPEHours(csvRecord.get(13));
				course.setCourseDuration(csvRecord.get(14));
				course.setMinutesVideoConsumed(csvRecord.get(15));
				if (StringUtils.isNotBlank(course.getUserEmail())) {
					courses.add(course);
				}
			}

		}

		logger.info("Ending CourseUploadService updateCourses()");

		return courses;

	}

}
