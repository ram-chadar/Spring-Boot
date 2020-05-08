package com.jbk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jbk.service.UserService;

@CrossOrigin
@RestController
@RequestMapping(value= "/jxl")
public class jxl_Controller {

	@Autowired
	UserService userService;
	
	@RequestMapping(value="/write")
	public String write_jxl() {
	String msg =	userService.write_jxl();
		return msg;
	}
}
