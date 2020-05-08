package com.jbk.controller;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jbk.bean.User;
import com.jbk.service.UserService;

@RestController
@RequestMapping(value = "/projection")
public class ProjectionController {
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/row")
	public ResponseEntity rowCount()
	 {
		List<User> cr=userService.rowCount();
		if(cr==null)
		{
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(cr,HttpStatus.OK);
	}

}
