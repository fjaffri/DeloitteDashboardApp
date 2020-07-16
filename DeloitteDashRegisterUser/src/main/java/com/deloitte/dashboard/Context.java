package com.deloitte.dashboard;

import java.io.File;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartFile;

import com.deloitte.dashboard.Model.Result;
import com.deloitte.dashboard.Model.User;
import com.deloitte.dashboard.service.InputFileProcessor;
/**
 * <h1>Context</h1> This class takes initialises an
 * interface and provides a generic method to read file
 * 
 * @author fjaffri
 * @version 1.0
 * @since 2020-07-15
 * 
 */
public class Context {
	private InputFileProcessor inputFileProcessor;

	
	public Context(InputFileProcessor inputFileProcessor) {
		
		this.inputFileProcessor = inputFileProcessor;
	}
	
	@Bean
	public Result readUserRegFile(MultipartFile file) {
		return inputFileProcessor.readUserRegFile(file);
	}
}