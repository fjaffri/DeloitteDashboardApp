package com.deloitte.dashboard.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.apache.poi.util.IOUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.deloitte.dashboard.Model.Result;
import com.deloitte.dashboard.Model.User;
import com.deloitte.dashboard.Repository.UserRegDao;
import com.deloitte.dashboard.Repository.UserRegRepository;
import com.deloitte.dashboard.service.FileUploadService;
import com.deloitte.dashboard.service.XlxsReader;


/**
 * <h1>Test cases for user registeration</>
 * This controller implements functionality
 * to register user in bulk in MongoDB
 * 
 * @author fjaffri
 * @version 1.0 
 * @since   2020-07-17
 * 
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserRegistrationTest {

	@InjectMocks
	FileUploadService fileUploadService;

	@Mock
	UserRegDao userRegDao;
	
	@Mock
	UserRegRepository repository;

	@InjectMocks
	XlxsReader xlxsReader;

	/** 
	    * This method is used to test whether data from excel is updaing in MongoDb or not. 
	    * Excel file with all user details are present in the resources.
	    * All new users will be saved in Mongo DB. 
	    */
	@Test
	public void saveUsersToDb() throws IOException {
		User user1 = new User();
		user1.set_id("fjay@deloitte.com");
		User user2 = new User();
		user2.set_id("vjay@deloitte.com");
		List<User> users = new ArrayList<>();
		users.add(user1);
		users.add(user2);

		Result res = new Result();
		res.setUsers(users);
		res.settotalRows(2);

		File file = new File(getClass().getClassLoader().getResource("inputFileWithTwoRows.xlsx").getFile());
		FileInputStream input = new FileInputStream(file);
		MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain",
				IOUtils.toByteArray(input));
		Result result = xlxsReader.readUserRegFile(multipartFile);
		doNothing().when(userRegDao).saveUserToDB(fileUploadService.readFileType(multipartFile));
		assertEquals(2, result.getUsers().size());
	}

	  /** 
	    * This method is used to test whether email id is present for a user in excel or not. 
	    * Excel file with one empty user email id is present in the resources.
	    * The method should only return a list with users having email id. 
	    */
	@Test
	public void excelFiledoesNotContainEmailIdTest() throws IOException {

		File file = new File(getClass().getClassLoader().getResource("inputFileWithOneEmailIDMissing.xlsx").getFile());
		FileInputStream input = new FileInputStream(file);
		MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain",
				IOUtils.toByteArray(input));

		Result result = xlxsReader.readUserRegFile(multipartFile);
		assertNotNull(result.getUsers());
		assertEquals(2, result.getUsers().size());

	}


	  /** 
	    * This method is used to test whether udpated or duplciate user details is updated in excel. 
	    * Excel file with two duplicate is present in the resources.
	    * The save method of mongo db will always perform Up-Sert operation
	    */
	@Test
	public void excelFileContainsDuplicateRecordTest() throws IOException {
		File file = new File(getClass().getClassLoader().getResource("inputFileWithTwoDuplicateUsers.xlsx").getFile());
		FileInputStream input = new FileInputStream(file);
		MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain",
				IOUtils.toByteArray(input));
		UserRegDao userRegDao = mock(UserRegDao.class);
		Result result = xlxsReader.readUserRegFile(multipartFile);
		doNothing().when(userRegDao).saveUserToDB(result);
		assertNotNull(result.getUsers());

		}
		
	}


