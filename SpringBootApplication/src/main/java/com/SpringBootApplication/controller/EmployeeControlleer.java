package com.SpringBootApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.SpringBootApplication.bean.Employee;
import com.SpringBootApplication.service.EmployeeService;


@RestController
@RequestMapping(value = "/employee")
public class EmployeeControlleer {
	
	@Autowired
	private EmployeeService employeeService;
	
	 @PostMapping(value="/save",headers="Accept=application/json")
	 public ResponseEntity<Void> saveEmployee(@RequestBody Employee employee, UriComponentsBuilder ucBuilder)
	{
		
		employeeService.saveEmployee(employee);
		
	     HttpHeaders headers = new HttpHeaders();
	     headers.setLocation(ucBuilder.path("/employee/{id}").buildAndExpand(employee.getEid()).toUri());
	 
		return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
		
	}
	 
	 @GetMapping(value = "/getAll")
	 public ResponseEntity getAllEmployee()
	 {
		List<Employee>empList= employeeService.getAllEmployee(); 
		if(empList.isEmpty())
		{
			return new ResponseEntity (HttpStatus.NOT_FOUND);
		}
		else
		{
			return new ResponseEntity (empList,HttpStatus.FOUND);
	
		}
		 
	 }

}
