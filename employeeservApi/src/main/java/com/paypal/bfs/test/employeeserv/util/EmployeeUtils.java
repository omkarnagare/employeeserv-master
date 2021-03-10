package com.paypal.bfs.test.employeeserv.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.DateOfBirth;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.entity.AddressEntity;
import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;


public class EmployeeUtils {
	
	public static final String NAME_REGEX = "^[A-Za-z]{1,255}$";
	public static final String CITY_STATE_REGEX = "^[A-Za-z]{1,100}$";
	public static final String COUNTRY_REGEX = "^[A-Za-z]{1,60}$";
	public static final String ZIP_REGEX = "^[0-9]{6,20}$";
	
	
	public static String isValidDate(final DateOfBirth dob) {
		if (dob == null) {
			return EmployeeAttributes.DOB_NULL.getErrorMessage();
		} else {
			Calendar cal = Calendar.getInstance();
			long now = cal.getTimeInMillis();
			cal.setLenient(false);
			cal.clear();
			cal.set(Calendar.YEAR, dob.getYear());
			cal.set(Calendar.MONTH, dob.getMonth()-1);
			cal.set(Calendar.DAY_OF_MONTH, dob.getDate());
			try {
			    long dobTime = cal.getTimeInMillis();
			    if (dobTime > now) {
			    	return EmployeeAttributes.DOB_FUTURE.getErrorMessage();
			    }
			} catch (Exception e) {
			  return EmployeeAttributes.DOB_INVALID.getErrorMessage();
			}
		}
		return null;
	}
	
	public static String isValidName(final String name, EmployeeAttributes empAttr) {
		return isMatching(name, NAME_REGEX) ? null: empAttr.getErrorMessage();
	}
	
	public static String isValidCityOrState(final String name, EmployeeAttributes attributes) {
		return isMatching(name, CITY_STATE_REGEX)? null : attributes.getErrorMessage();
	}
	
	public static String isValidCountry(final String name) {
		return isMatching(name, COUNTRY_REGEX)? null : EmployeeAttributes.ADDRESS_CONUTRY.getErrorMessage();
	}
	
	public static String isValidZipCode(final String name) {
		return isMatching(name, ZIP_REGEX)? null : EmployeeAttributes.ADDRESS_ZIP_CODE.getErrorMessage();
	}
	
	public static boolean isMatching(String s, String regex) {
		if (StringUtils.isEmpty(s)) {
			return false;
		} else {
			Pattern p = Pattern.compile(regex); 
			Matcher m = p.matcher(s); 
			return m.matches();
		}
	}
	
	public static List<String> isValid(Employee employee) {
		List<String> errors = new ArrayList<String>();
		errors.add(isValidName(employee.getFirstName(), EmployeeAttributes.FIRST_NAME));
		errors.add(isValidName(employee.getLastName(), EmployeeAttributes.LAST_NAME));
		errors.add(isValidDate(employee.getDateOfBirth()));
		errors.addAll(isValidAddress(employee.getAddress()));		
		return errors.stream().filter(StringUtils::isNotEmpty).collect(Collectors.toList());
	}
	
	private static List<String> isValidAddress(Address address) {
		List<String> errors = new ArrayList<String>();
		if (address == null) {
			errors.add(EmployeeAttributes.ADDRESS_NULL.getErrorMessage());
		} else {
			errors.add(StringUtils.isNotEmpty(address.getLine1()) ? null: EmployeeAttributes.ADDRESS_LINE_1.getErrorMessage());
			errors.add(isValidCityOrState(address.getCity(), EmployeeAttributes.ADDRESS_CITY));
			errors.add(isValidCityOrState(address.getState(), EmployeeAttributes.ADDRESS_STATE));
			errors.add(isValidCountry(address.getCountry()));
			errors.add(isValidZipCode(address.getZipCode()));
		}
		return errors.stream().filter(StringUtils::isNotEmpty).collect(Collectors.toList());
	}

	public static Employee entityToModel(EmployeeEntity empEntity) {
		Employee employee = new Employee();
		employee.setId(empEntity.getId().intValue());
		employee.setFirstName(empEntity.getFirstName());
		employee.setLastName(empEntity.getLastName());
		
		DateOfBirth dob = new DateOfBirth();
		dob.setDate(empEntity.getDobDay());
		dob.setMonth(empEntity.getDobMonth());
		dob.setYear(empEntity.getDobYear());
		employee.setDateOfBirth(dob);
		
		Address address = new Address();
		AddressEntity addressEntity = empEntity.getAddress();
		address.setLine1(addressEntity.getLine1());
		address.setLine2(addressEntity.getLine2());
		address.setCity(addressEntity.getCity());
		address.setState(addressEntity.getState());
		address.setCountry(addressEntity.getCountry());
		address.setZipCode(addressEntity.getZipCode());
		employee.setAddress(address);
		
		return employee;
	}
	
	public static EmployeeEntity modelToEntity(Employee employee) {
		EmployeeEntity empEntity = new EmployeeEntity();
		if (employee.getId() != null) { 
			// ignoring for post calls
			empEntity.setId(Long.valueOf(employee.getId()));
		}
		empEntity.setFirstName(employee.getFirstName());
		empEntity.setLastName(employee.getLastName());
		
		DateOfBirth dob = employee.getDateOfBirth();
		empEntity.setDobDay(dob.getDate());
		empEntity.setDobMonth(dob.getMonth());
		empEntity.setDobYear(dob.getYear());
		
		AddressEntity addressEntity = new AddressEntity();
		Address address = employee.getAddress();
		addressEntity.setLine1(address.getLine1());
		addressEntity.setLine2(address.getLine2());
		addressEntity.setCity(address.getCity());
		addressEntity.setState(address.getState());
		addressEntity.setCountry(address.getCountry());
		addressEntity.setZipCode(address.getZipCode());
		empEntity.setAddress(addressEntity);
		
		return empEntity;
	}

}
