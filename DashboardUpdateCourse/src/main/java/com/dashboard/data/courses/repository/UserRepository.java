package com.dashboard.data.courses.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dashboard.data.courses.model.User;


public interface UserRepository extends MongoRepository<User, String>{
	  User findBy_id(String _id);

}
