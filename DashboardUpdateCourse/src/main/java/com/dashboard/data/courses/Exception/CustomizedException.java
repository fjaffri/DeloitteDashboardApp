package com.dashboard.data.courses.Exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@ControllerAdvice
public class CustomizedException  {
	
	 @ExceptionHandler(ItemNotFoundException.class)
	  public final ResponseEntity<ErrorDetails> handleUserNotFoundException(ItemNotFoundException ex) {
	    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),"error");
	    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	  }
	
	

}
