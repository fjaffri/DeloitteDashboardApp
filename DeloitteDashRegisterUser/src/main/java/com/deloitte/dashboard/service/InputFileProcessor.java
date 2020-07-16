package com.deloitte.dashboard.service;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.deloitte.dashboard.Model.Result;
import com.deloitte.dashboard.Model.User;
/**
 * <h1>InputFileProcessor interface</h1> This interface provides a method
 * to parse the uploaded file. File can be in differnet format
 * 
 * @author fjaffri
 * @version 1.0
 * @since 2020-07-15
 * 
 */
public interface InputFileProcessor{
	
	public Result readUserRegFile(MultipartFile file);
	

}
