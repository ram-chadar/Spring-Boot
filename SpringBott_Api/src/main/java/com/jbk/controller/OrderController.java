package com.jbk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jbk.bean.User;
import com.jbk.service.UserService;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/desc")
	public ResponseEntity<User> desc()
	{
		List<User>list=userService.desc();
		if(list.isEmpty())
		{
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(list,HttpStatus.OK);
	}
	

}
