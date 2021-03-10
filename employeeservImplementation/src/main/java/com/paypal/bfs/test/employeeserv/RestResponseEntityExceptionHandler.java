package com.paypal.bfs.test.employeeserv;

import java.util.NoSuchElementException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.paypal.bfs.test.employeeserv.api.model.ErrorResponse;
import com.paypal.bfs.test.employeeserv.exception.InvalidEmployeeException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { InvalidEmployeeException.class })
	protected ResponseEntity<Object> handleInvalidEmployee(
			RuntimeException ex, WebRequest request) {
		InvalidEmployeeException invalidEmpEx = (InvalidEmployeeException) ex;
		
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrors(invalidEmpEx.getErrors());
		errorResponse.setErrorMessage(invalidEmpEx.getMessage());
		
		return handleExceptionInternal(ex, errorResponse, 
				new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(value = { EmptyResultDataAccessException.class, NoSuchElementException.class })
	protected ResponseEntity<Object> handleEmployeeNotFound (
			RuntimeException ex, WebRequest request) {		
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorMessage("There is no such Employee with given id");
		
		return handleExceptionInternal(ex, errorResponse, 
				new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(value = { NumberFormatException.class })
	protected ResponseEntity<Object> handleInvalidIds (
			RuntimeException ex, WebRequest request) {		
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorMessage("Employee id should be an valid integer");
		
		return handleExceptionInternal(ex, errorResponse, 
				new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
}
