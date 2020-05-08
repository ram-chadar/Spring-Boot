package com.ram.service;

import java.io.Serializable;
import java.util.List;

import com.ram.exception.RecordNotFoundException;
import com.ram.model.EmployeeEntity;

public interface EmployeeService {

	public Serializable saveEmployee(EmployeeEntity employeeEntity);
	
	public EmployeeEntity getEmployeeById(int id) throws RecordNotFoundException;
	
	public void deleteEmployee(int id);
	
	public void updateEmployee(EmployeeEntity employeeEntity);
	
	public List<EmployeeEntity>getAllEmployee();
}
