package com.paypal.bfs.test.employeeserv.exception;

import java.util.ArrayList;
import java.util.List;

public class InvalidEmployeeException extends RuntimeException{
	
	private List<String> errors;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidEmployeeException(String mesage, Throwable t) {
		super(mesage, t);
	}
	
	public InvalidEmployeeException(List<String> errors, String mesage) {
		super(mesage);
		this.errors = new ArrayList<String>(errors);
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
}
