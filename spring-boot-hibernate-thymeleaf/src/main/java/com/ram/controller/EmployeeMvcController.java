package com.ram.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ram.exception.RecordNotFoundException;
import com.ram.model.EmployeeEntity;
import com.ram.service.EmployeeService;

@Controller
@RequestMapping("/")
public class EmployeeMvcController 
{
	@Autowired
	private EmployeeService service;

	@RequestMapping
	public String getAllEmployees(Model model) 
	{
		List<EmployeeEntity> list = service.getAllEmployee();

		model.addAttribute("employees", list);
		return "list-employees";
	}

	@RequestMapping(value="/saveForm")
	public ModelAndView editEmployeeById() 
							 
	{
		return new ModelAndView("add");
	}
	
	@RequestMapping(path = "/delete/{id}")
	public String deleteEmployeeById(Model model, @PathVariable("id") int id) 
							throws RecordNotFoundException 
	{
		service.deleteEmployee(id);
		return "redirect:/";
	}
	
	@RequestMapping(path = "/edit/{id}")
	public String getEmployeeForUpdate(Model model, @PathVariable("id") int id) 
							throws RecordNotFoundException 
	{
		EmployeeEntity  employeeEntity=service.getEmployeeById(id);
		model.addAttribute("employee",employeeEntity);
		return "edit";
	}

	@RequestMapping(path = "/createEmployee", method = RequestMethod.POST)
	public String createOrUpdateEmployee(@ModelAttribute("model") EmployeeEntity employee) 
	{
		service.saveEmployee(employee);
		return "redirect:/";
	}
	
	@RequestMapping(path = "/editEmployee", method = RequestMethod.POST)
	public String editEmployee(@ModelAttribute("model") EmployeeEntity employee) 
	{
		System.out.println(employee);
		System.out.println("***************"+employee.getId()+employee.getFirstName());
		service.updateEmployee(employee);
		return "redirect:/";
	}
	
	
}
