package com.ram.repository;

import java.io.Serializable;
import java.util.List;

import com.ram.model.EmployeeEntity;

public interface EmployeeRepository {

public Serializable saveEmployee(EmployeeEntity employeeEntity);
	
	public EmployeeEntity getEmployeeById(int id);
	
	public void deleteEmployee(int id);
	
	public void updateEmployee(EmployeeEntity employeeEntity);
	
	public List<EmployeeEntity>getAllEmployee();

}
