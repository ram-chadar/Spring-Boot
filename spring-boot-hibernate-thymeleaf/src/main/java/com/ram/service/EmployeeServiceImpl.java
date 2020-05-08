package com.ram.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ram.exception.RecordNotFoundException;
import com.ram.model.EmployeeEntity;
import com.ram.repository.EmployeeRepository;

@Service

public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<EmployeeEntity> getAllEmployee() {
		List<EmployeeEntity> result = (List<EmployeeEntity>) employeeRepository.getAllEmployee();

		if (result.size() > 0) {
			return result;
		} else {
			return new ArrayList<EmployeeEntity>();
		}
	}

	public EmployeeEntity getEmployeeById(int id) throws RecordNotFoundException {
		EmployeeEntity employee = employeeRepository.getEmployeeById(id);

		if (employee != null) {
			return employee;
		} else {
			throw new RecordNotFoundException("No employee record exist for given id");
		}
	}

	public Serializable saveEmployee(EmployeeEntity entity) {

		Serializable id = employeeRepository.saveEmployee(entity);
		return id;

	}

	public void deleteEmployee(int id)  {
		employeeRepository.deleteEmployee(id);
	
	}
	
	public void updateEmployee(EmployeeEntity employeeEntity)
	{
		employeeRepository.updateEmployee(employeeEntity);
	}

	
}