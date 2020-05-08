package com.SpringBootApplication.service;

import java.util.List;

import com.SpringBootApplication.bean.Employee;


public interface EmployeeService {
	
	public void saveEmployee( Employee employee);
	
	public List<Employee> getAllEmployee();
	

}
