package com.deloitte.dashboard.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.aspectj.weaver.NewFieldTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.deloitte.dashboard.Exception.ItemNotFoundException;
import com.deloitte.dashboard.Model.Course;
import com.deloitte.dashboard.Model.Result;
import com.deloitte.dashboard.Model.User;
import com.deloitte.dashboard.Repository.UserRegRepository;
import com.deloitte.dashboard.service.FileUploadService;

/**
 * <h1>File Upload Controller</> This controller implements functionality to
 * register user in bulk in MongoDB
 * 
 * @author fjaffri
 * @version 1.0
 * @since 2020-07-15
 * 
 */
@EnableEurekaClient
@RestController
public class FileUploadController {
	@Autowired
	FileUploadService fileUploadService;
	@Autowired
	UserRegRepository repository;

	/**
	 * This API is used to perform bulk registration in MongoDB
	 * 
	 * @param An xlxs file which contains users for registration
	 * @return String This returns a messege showing success status.
	 */
	@PostMapping("/user")
	public String singleFileUpload(@RequestParam("file") MultipartFile file) {
		long time = System.currentTimeMillis();
		
		Result res = fileUploadService.readFileType(file);
		if (res.getUsers() == null || res.getUsers().isEmpty()) {
			throw new ItemNotFoundException("File contains no data");
		}

		Thread updateMongoDb = new Thread(new Runnable() {
			
			@Override
			public void run() {
				repository.saveAll(res.getUsers());
				
			}
		});
		updateMongoDb.start();
	
		int totalRowsInExcel = res.gettotalRows();
		int insertedRowsinDb = res.getUsers().size();
		if (totalRowsInExcel != insertedRowsinDb) {
			int rowsNotUpdated = totalRowsInExcel - insertedRowsinDb;
			return rowsNotUpdated + " rows not updated in DB due to missing email id/duplicate record";
		}
		long responseTime = (System.currentTimeMillis() - time);
		System.out.println("Execution time for bulk register in controller "+responseTime);
		return "All rows inserted successfully in DB";
	}

	@DeleteMapping("/users")
	public ResponseEntity<HttpStatus> deleteAllUsers() {
	  try {
		  repository.deleteAll();
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  } catch (Exception e) {
	    return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	  }
	}
}
