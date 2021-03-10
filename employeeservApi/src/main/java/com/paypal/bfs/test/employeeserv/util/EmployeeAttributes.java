package com.paypal.bfs.test.employeeserv.util;

public enum EmployeeAttributes {
	
	FIRST_NAME("First name should contain only alphabets, max 255 chars are allowed"),
	LAST_NAME("Last name should contain only alphabets, max 255 chars are allowed"),
	DOB_NULL ("Employee should must have DOB"),
	DOB_INVALID ("Employee should have valid DOB"),
	DOB_FUTURE ("Employee should not have DOB as today or in the future"),
	ADDRESS_NULL ("Employee should must have valid Address"),
	ADDRESS_LINE_1("Address Line 1 should not be empty"),
	ADDRESS_CITY("Address should must have a valid City. It should contain only alphabets, max 100 chars are allowed"),
	ADDRESS_STATE("Address should must have a valid State. It should contain only alphabets, max 100 chars are allowed"),
	ADDRESS_CONUTRY("Address should must have a valid Country. It should contain only alphabets, max 60 chars are allowed"),
	ADDRESS_ZIP_CODE("Address sholud must have a valid zip code. It should contain atleast 6 numeric characters, max 20 chars are allowed")
	;
	
	private final String errorMessage;
	
	EmployeeAttributes(String type) {
		this.errorMessage = type;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
