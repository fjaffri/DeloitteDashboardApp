package com.deloitte.dashboard.Repository;

import java.util.HashMap;
import java.util.HashSet;

import org.apache.poi.util.SystemOutLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.deloitte.dashboard.Model.Result;
import com.deloitte.dashboard.Model.User;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

@Component
public class UserRegDao {

	@Autowired
	UserRegRepository repository;
	public void saveUserToDB(Result result){
		    long time = System.currentTimeMillis();
			repository.saveAll( result.getUsers());
			long responseTime = (System.currentTimeMillis() - time);
			System.out.println("Execution time for uploading courses associated with users "+responseTime);

		
	}


	
}
