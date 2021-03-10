package com.paypal.bfs.test.employeeserv;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.paypal.bfs.test.employeeserv.api.model.DateOfBirth;
import com.paypal.bfs.test.employeeserv.util.EmployeeAttributes;
import com.paypal.bfs.test.employeeserv.util.EmployeeUtils;

@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeTest {
	
	@Test
	public void testDateOfBirths() {
		
		DateOfBirth dob = new DateOfBirth();
		dob.setDate(45);
		dob.setMonth(12);
		dob.setYear(1991);
		Assert.assertEquals(EmployeeAttributes.DOB_INVALID.getErrorMessage(), EmployeeUtils.isValidDate(dob));
		
		dob.setDate(20);
		Assert.assertNull(EmployeeUtils.isValidDate(dob));
		
		dob.setYear(2050);
		Assert.assertEquals(EmployeeAttributes.DOB_FUTURE.getErrorMessage(), EmployeeUtils.isValidDate(dob));
		Assert.assertEquals(EmployeeAttributes.DOB_NULL.getErrorMessage(), EmployeeUtils.isValidDate(null));
	}
	
	@Test
	public void testName() {
		Assert.assertEquals(EmployeeAttributes.FIRST_NAME.getErrorMessage(), EmployeeUtils.isValidName(null, EmployeeAttributes.FIRST_NAME));
		Assert.assertEquals(EmployeeAttributes.FIRST_NAME.getErrorMessage(), EmployeeUtils.isValidName("", EmployeeAttributes.FIRST_NAME));
		Assert.assertEquals(EmployeeAttributes.FIRST_NAME.getErrorMessage(), EmployeeUtils.isValidName("bvsvbhj868vdsv", EmployeeAttributes.FIRST_NAME));
		
		char[] array = new char[256];
		for (int i = 0; i< array.length; i++) {
			array[i]= 'o';
		}
		
		Assert.assertEquals(EmployeeAttributes.FIRST_NAME.getErrorMessage(), EmployeeUtils.isValidName(new String(array), EmployeeAttributes.FIRST_NAME));
		Assert.assertNull(EmployeeUtils.isValidName("Omkar", EmployeeAttributes.FIRST_NAME));
	}
	
	@Test
	public void testCity() {
		Assert.assertEquals(EmployeeAttributes.ADDRESS_CITY.getErrorMessage(), EmployeeUtils.isValidCityOrState(null, EmployeeAttributes.ADDRESS_CITY));
		Assert.assertEquals(EmployeeAttributes.ADDRESS_CITY.getErrorMessage(), EmployeeUtils.isValidCityOrState("", EmployeeAttributes.ADDRESS_CITY));
		Assert.assertEquals(EmployeeAttributes.ADDRESS_CITY.getErrorMessage(), EmployeeUtils.isValidCityOrState("bvsvbhj868vdsv", EmployeeAttributes.ADDRESS_CITY));
		
		char[] array = new char[101];
		for (int i = 0; i< array.length; i++) {
			array[i]= 'o';
		}
		
		Assert.assertEquals(EmployeeAttributes.ADDRESS_CITY.getErrorMessage(), EmployeeUtils.isValidCityOrState(new String(array), EmployeeAttributes.ADDRESS_CITY));
		Assert.assertNull(EmployeeUtils.isValidCityOrState("Thane", EmployeeAttributes.ADDRESS_CITY));
	}
	
	@Test
	public void testConutry() {
		Assert.assertEquals(EmployeeAttributes.ADDRESS_CONUTRY.getErrorMessage(), EmployeeUtils.isValidCountry(null));
		Assert.assertEquals(EmployeeAttributes.ADDRESS_CONUTRY.getErrorMessage(), EmployeeUtils.isValidCountry(""));
		Assert.assertEquals(EmployeeAttributes.ADDRESS_CONUTRY.getErrorMessage(), EmployeeUtils.isValidCountry("bvsvbhj868vdsv"));
		
		char[] array = new char[61];
		for (int i = 0; i< array.length; i++) {
			array[i]= 'o';
		}
		
		Assert.assertEquals(EmployeeAttributes.ADDRESS_CONUTRY.getErrorMessage(), EmployeeUtils.isValidCountry(new String(array)));
		Assert.assertNull(EmployeeUtils.isValidCountry("India"));
	}
	
	@Test
	public void testZipCode() {
		Assert.assertEquals(EmployeeAttributes.ADDRESS_ZIP_CODE.getErrorMessage(), EmployeeUtils.isValidZipCode(null));
		Assert.assertEquals(EmployeeAttributes.ADDRESS_ZIP_CODE.getErrorMessage(), EmployeeUtils.isValidZipCode(""));
		Assert.assertEquals(EmployeeAttributes.ADDRESS_ZIP_CODE.getErrorMessage(), EmployeeUtils.isValidZipCode("bvsvbhj868vdsv"));
		Assert.assertEquals(EmployeeAttributes.ADDRESS_ZIP_CODE.getErrorMessage(), EmployeeUtils.isValidZipCode("40014"));
		
		char[] array = new char[21];
		for (int i = 0; i< array.length; i++) {
			array[i]= '5';
		}
		
		Assert.assertEquals(EmployeeAttributes.ADDRESS_ZIP_CODE.getErrorMessage(), EmployeeUtils.isValidZipCode(new String(array)));
		Assert.assertNull(EmployeeUtils.isValidZipCode("400615"));
	}
	
}
