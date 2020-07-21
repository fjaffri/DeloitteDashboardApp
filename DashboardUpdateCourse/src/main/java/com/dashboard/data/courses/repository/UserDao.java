package com.dashboard.data.courses.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dashboard.data.courses.Exception.ItemNotFoundException;
import com.dashboard.data.courses.model.Course;
import com.dashboard.data.courses.model.User;

/**
 * <h1>UserDao Class for querying to MongoDB</> This class implements
 * functionality of updating and fetching data from database
 * 
 * @author fjaffri
 * @version 1.0
 * @since 2020-07-15
 * 
 */
@Component
public class UserDao {

	@Autowired
	UserRepository repository;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * This method iterates through the List of Courses and associate each
	 * registered user to courses which he/she has registered for
	 * 
	 * @param List of Courses each user is registered to
	 * @return void
	 */

	public void updateDB(List<Course> courses) {
		logger.info("In UserDao updateDB()");
		Map<String, List<Course>> map = storeInMap(courses);
		for (Entry<String, List<Course>> mapData : map.entrySet()) {

			User regUser = repository.findBy_id(mapData.getKey());
			if (regUser != null) {
				regUser.setCourse(mapData.getValue());
				repository.save(regUser);

			}

		}
		logger.info("Ending UserDao updateDB()");

	}

	/**
	 * This method iterates through the List of Courses, creates a dictionary of
	 * email id and List of Courses. The dictionary stores that how many courses a
	 * user with single email is registered for.
	 * 
	 * @param Takes list of Courses each user is associated to as input
	 * @return Map of email id and list of courses that user is registered to
	 */
	public Map<String, List<Course>> storeInMap(List<Course> courses) {
		logger.info("In UserDao storeInMap()");

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
		logger.info("Ending UserDao storeInMap()");

		return map;

	}

	public List<User> getAllDataFromDB() {
		logger.info("In UserDao getAllDataFromDB()");
		List<User> users = repository.findAll();
		if (users == null || users.isEmpty()) {
			throw new ItemNotFoundException("No user data available");

		}
		return users;

	}

}
