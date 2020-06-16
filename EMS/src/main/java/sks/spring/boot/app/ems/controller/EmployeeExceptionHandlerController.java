package sks.spring.boot.app.ems.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import sks.spring.boot.app.ems.exception.EmployeeNotFoundException;

@ControllerAdvice
public class EmployeeExceptionHandlerController {

	// Handle Exception Globally
	
	@ExceptionHandler(value = EmployeeNotFoundException.class )
	public ResponseEntity<Object> exception(EmployeeNotFoundException ex){
		return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.OK);
	}
	
	
	
	
}
