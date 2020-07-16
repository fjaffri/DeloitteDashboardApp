package com.dashboard.service;

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
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dashboard.model.Course;


@Service
public class CourseUploadService {

	public List<Course> updateCourses(MultipartFile file) throws IOException {
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

		return courses;

	}
	
	public Map<String, List<Course>> storeInMap(List<Course> courses) {

		Map<String, List<Course>> map = new HashMap<>();
		for (Course course : courses) {
			if (map.containsKey(course.getUserEmail())) {
				List<Course> res = map.get(course.getUserEmail());
				res.add(course);
				map.put(course.getUserEmail(), res);
			} else {
				List<Course> results = new ArrayList<>();
				results.add(course);
				map.put(course.getUserEmail(), results);
			}
		}
		return map;

	}

}
