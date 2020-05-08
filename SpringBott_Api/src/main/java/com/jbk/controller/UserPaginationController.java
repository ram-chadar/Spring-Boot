package com.jbk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jbk.bean.User;
import com.jbk.service.UserService;

@RestController
@RequestMapping(value = "/page")
public class UserPaginationController {
	
	@Autowired
	UserService userService;
	
	@GetMapping(value = "/{from}/{to}")
	public ResponseEntity<User>page(@PathVariable("from") int from,@PathVariable("to")int to)
	{
		System.out.println("***********************************");
		List<User>list=userService.paging(from,to);
		if(list.isEmpty())
		{
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(list,HttpStatus.OK);
	}
}
