package com.deloitte.dashboard.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.deloitte.dashboard.Model.Result;
import com.deloitte.dashboard.Model.User;
import com.deloitte.dashboard.Repository.UserRegRepository;

/**
 * <h1>Excel File Reader</>
 * This class implements functionality
 * of parsing the xlxs file.  
 * 
 * @author fjaffri
 * @version 1.0 
 * @since   2020-07-15 
 * 
 */
@Component
public class XlxsReader implements InputFileProcessor {

	@Autowired
	UserRegRepository repository;
	
	 /** 
	    * This method reads data from xlsx file, and maps it to User Object 
	    * @param An xlxs file which contains users info for registration 
	    * @return Object This returns a result object binded with List of Users. 
	    */
	@Override
	public Result readUserRegFile(MultipartFile file) {
		byte[] byteArr = null;
		try {
			byteArr = file.getBytes();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ByteArrayInputStream fis = null;
		fis = new ByteArrayInputStream(byteArr);
		// we create an XSSF Workbook object for our XLSX Excel File
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// we get first sheet
		Result result = new Result();
		XSSFSheet sheet = workbook.getSheetAt(0);
		int totalRows = sheet.getPhysicalNumberOfRows();
		int count=0;

		List<User> users = new ArrayList<>();	
		Iterator<Row> rowIt = sheet.rowIterator();
		while (rowIt.hasNext()) {
			Row row = rowIt.next();
			User user = new User();
			if (row.getRowNum() > 0) {				
				
				user.set_id((row.getCell(0).getStringCellValue()));
				user.setFirstName(row.getCell(1).getStringCellValue());
				user.setLastName(row.getCell(2).getStringCellValue());
				user.setGeoLocation(row.getCell(3).getStringCellValue());
				user.setMemberFirm(row.getCell(4).getStringCellValue());
				user.setBusiness(row.getCell(5).getStringCellValue());
				user.setRegion(row.getCell(6).getStringCellValue());
				user.setConsultingLevel(row.getCell(7).getStringCellValue());
				user.setAccountCapability(row.getCell(8).getStringCellValue());
				user.setCurrentPmName(row.getCell(9).getStringCellValue());
				user.setCurrentPmEmail(row.getCell(10).getStringCellValue());
				user.setCoach(row.getCell(11).getStringCellValue());
				user.setCoachEmail(row.getCell(12).getStringCellValue());
				user.setLeader(row.getCell(13).getStringCellValue());
				user.setLearningCoach(row.getCell(14).getStringCellValue());
				user.setAcknowledgemnet(row.getCell(15).getStringCellValue());
				user.setFullyAvailable(row.getCell(16).getStringCellValue());
				user.setReason(row.getCell(17).getStringCellValue());
				if(StringUtils.isNotBlank(user.get_id())) {
					count++;
					users.add(user);
				}
				
			}
		}
		result.setUsers(users);
		result.settotalRows(totalRows-1);
		return result;
		
				
	}


}
