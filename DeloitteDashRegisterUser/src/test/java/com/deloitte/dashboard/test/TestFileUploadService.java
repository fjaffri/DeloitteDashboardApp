package com.deloitte.dashboard.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.deloitte.dashboard.service.FileUploadService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TestFileUploadService {

	@InjectMocks
	FileUploadService fileUploadService;

	@Mock
	UserRegDao userRegDao;

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

		doNothing().when(userRegDao).saveUserToDB(fileUploadService.readFileType(multipartFile));
		assertEquals(2, res.getUsers().size());
	}

}
