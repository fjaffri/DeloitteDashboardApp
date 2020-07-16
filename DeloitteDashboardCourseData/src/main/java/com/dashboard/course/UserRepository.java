package com.dashboard.course;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import com.dashboard.model.User;


public interface UserRepository extends MongoRepository<User, String>{
	  User findBy_id(String _id);

}
