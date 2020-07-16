package com.deloitte.dashboard.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import com.deloitte.dashboard.Model.User;


@Component
public interface UserRegRepository extends MongoRepository<User, String>{
	  User findBy_id(String _id);

}
