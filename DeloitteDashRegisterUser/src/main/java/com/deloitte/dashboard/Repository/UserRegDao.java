package com.deloitte.dashboard.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.deloitte.dashboard.Model.Result;
import com.deloitte.dashboard.Model.User;

@Component
public class UserRegDao {

	@Autowired
	UserRegRepository repository;
	
	
	public void saveUserToDB(Result result){
		
		for (User user : result.getUsers()) {
			repository.save(user);
		}
	}
	
}
