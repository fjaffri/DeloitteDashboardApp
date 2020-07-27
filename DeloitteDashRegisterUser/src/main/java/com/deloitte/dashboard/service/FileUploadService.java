package com.deloitte.dashboard.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.deloitte.dashboard.Model.Course;
import com.deloitte.dashboard.Model.Result;
import com.deloitte.dashboard.Model.User;
import com.deloitte.dashboard.Repository.UserRegDao;
import com.deloitte.dashboard.Context;
import com.deloitte.dashboard.Exception.ItemNotFoundException;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * <h1>File Upload Service</h1> This class reades the file and sends it to
 * differnt methods according to the input file type
 * 
 * @author fjaffri
 * @version 1.0
 * @since 2020-07-15
 * 
 */
@Component
public class FileUploadService {

	@Autowired
	private UserRegDao userRegDao;

	/**
	 * This method reads data from xlsx file, and register it to MongoDb
	 * 
	 * @param An xlxs file which contains users info for registration
	 * @return Object This returns a result object binded with List of Users.
	 */

	public Result readFileType(MultipartFile file) {
		if (file.getOriginalFilename().endsWith("xlsx")) {
			Context c = new Context(new XlxsReader());
			Result result = c.readUserRegFile(file);
//			if (result != null) {
//				//userRegDao.saveUserToDB(result);
//			}
			return result;

		} else if (file.getOriginalFilename().endsWith("xml")) {
			// context.setTransaction(new XMLReader());

		} else if (file.getOriginalFilename().endsWith("txt")) {
			//
		}
		throw new ItemNotFoundException("Please upload an XLXS file");

	}

}
