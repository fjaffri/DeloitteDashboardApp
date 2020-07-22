package com.dashboard.data.courses.services;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dashboard.data.courses.model.Course;
import com.dashboard.data.courses.model.User;
import com.opencsv.CSVWriter;

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
	 * @param A csv file which contains courses details attended by each registered
	 *          user.
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

	/**
	 * This method iterates through the list of Users fetced from DB and maps writes
	 * to CSV File
	 * 
	 * @param List of user that contains participants data
	 * 
	 * @return String Success message if the data is written to CSV
	 */
	public String writeDataAtOnce(List<User> users) {
		

		// File name where participant data will be written
		File file = new File("ParticipintsData.csv");

		try {
			// create FileWriter object with file as parameter
			FileWriter outputfile = new FileWriter(file);

			// create CSVWriter object filewriter object as parameter
			CSVWriter writer = new CSVWriter(outputfile);
			List<String[]> data = new ArrayList<String[]>();
			
			// Adding Header data
			
			data.add(new String[] { "Email_id", "First Name", "Last Name", "GeoLocation", "Member Firm", "Business",
					"Region", "Consulting Level", "Account Capability", "Current PM", "Current PM Email", "Coach",
					"CoachEmail", "Leader", "Learning Coach", "Acknowledgement", "Availability", "Reason",
					"Percentage Marked", "Course Category", "Course ID", "Course Title", "Date Completed",
					"Date Enrolled", "Date First Completed", "Date Last Accessed", "Date Started", "LastName",
					"First Name", "User Email", "Year of Range", "Course Duration CPE Hours", "Course Duration",
					"Minutes Video Consumed" });
			
			//Iterating each user and course list, writing to CSV file
			
			for (User user : users) {

				if (!user.getCourse().isEmpty()) {

					for (Course course : user.getCourse()) {

						data.add(new String[] { user.get_id(), user.getFirstName(), user.getLastName(),
								user.getGeoLocation(), user.getMemberFirm(), user.getBusiness(), user.getRegion(),
								user.getConsultingLevel(), user.getAccountCapability(), user.getCurrentPmName(),
								user.getCurrentPmEmail(), user.getCoach(), user.getCoachEmail(), user.getLeader(),
								user.getLearningCoach(), user.getAcknowledgemnet(), user.getFullyAvailable(),
								user.getReason(), course.getPercentMarked(), course.getCourseCatagory(),
								course.getCourseId(), course.getCourseTitle(), course.getDateCompleted(),
								course.getDateEnrolled(), course.getDateFirstCompleted(), course.getDateLastAccesed(),
								course.getDateStarted(), course.getUserLastName(), course.getUserFirstName(),
								course.getUserEmail(), course.getYearOfRangeDate(), course.getCourseDurationCPEHours(),
								course.getCourseDuration(), course.getMinutesVideoConsumed() });

					}

				} else {
					data.add(new String[] { user.get_id(), user.getFirstName(), user.getLastName(),
							user.getGeoLocation(), user.getMemberFirm(), user.getBusiness(), user.getRegion(),
							user.getConsultingLevel(), user.getAccountCapability(), user.getCurrentPmName(),
							user.getCurrentPmEmail(), user.getCoach(), user.getCoachEmail(), user.getLeader(),
							user.getLearningCoach(), user.getAcknowledgemnet(), user.getFullyAvailable(),
							user.getReason() });

				}

			}

			writer.writeAll(data);
			writer.close();

			return "success";

			// closing writer connection

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "Error writing data";
	}

}
