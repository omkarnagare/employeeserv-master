package com.paypal.bfs.test.employeeserv.service;

import java.util.List;
import java.util.Optional;

import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;

public interface EmployeeService {
	
	public EmployeeEntity saveEmployee(EmployeeEntity employee);
	
	public List<EmployeeEntity> listEmployees();
	
	public Optional<EmployeeEntity> getEmployee(int id);
	
	public Optional<EmployeeEntity> getEmployee(EmployeeEntity employee);
	
	public void deleteEmployee(int id);

}
