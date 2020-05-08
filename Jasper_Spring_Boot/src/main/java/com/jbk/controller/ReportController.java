package com.jbk.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jbk.service.UserService;

@CrossOrigin
@RestController
@RequestMapping(value={"/report"})
public class ReportController {
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/{format}")
	public String generateReport(@PathVariable(name="format")String format,HttpServletResponse response){
	String msg =	userService.generateReport(format,response);
		
		return msg;
	}
	
	
}
