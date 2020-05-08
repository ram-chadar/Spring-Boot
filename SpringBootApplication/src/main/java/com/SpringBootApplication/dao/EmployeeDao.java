package com.SpringBootApplication.dao;

import java.util.List;

import com.SpringBootApplication.bean.Employee;

public interface EmployeeDao {
	
	public void saveEmployee(Employee employee);
	public List<Employee> getAllEmployee();


}
