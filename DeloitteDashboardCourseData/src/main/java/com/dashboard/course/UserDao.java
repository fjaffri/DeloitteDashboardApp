package com.dashboard.course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dashboard.model.Course;
import com.dashboard.model.User;

public class UserDao {

	private final UserRepository repository;

	public UserDao() {
		this.repository = null;
		// TODO Auto-generated constructor stub
	}
	public UserDao(UserRepository repository) {
		super();
		this.repository = repository;
	}

	public void updateDB(List<Course> courses) {
		Map<String, List<Course>> map = storeInMap(courses);
		for (Entry<String, List<Course>> mapData : map.entrySet()) {
			System.out.println(mapData.getKey() + "---" + mapData.getValue());

			User regUser = repository.findBy_id(mapData.getKey());
			if (regUser != null) {
				regUser.setCourse(mapData.getValue());
				repository.save(regUser);
			}

		}

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
