package com.jbk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jbk.bean.User;
import com.jbk.service.UserService;

@CrossOrigin
@RestController
@RequestMapping(value={"/restriction"})
public class UserRestrictionController {
	@Autowired
	UserService userService;
	
	//less than
		@GetMapping(value = "/less/{id}")
		public ResponseEntity<User>lessThan(@PathVariable("id") int id)
		{
			List<User>userlist=userService.lessThan(id);
			
			if(userlist.isEmpty())
			{
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity(userlist,HttpStatus.OK);
			
		}
		@GetMapping(value = "/like/{name}")
		public ResponseEntity<User> likeName(@PathVariable("name") String name)
		{
			List<User>list=userService.likeName(name);
			if(list.isEmpty())
			{
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity(list,HttpStatus.OK);
		}
		
		@GetMapping(value = "/between/{first}/{second}")
		public ResponseEntity<User>betweenQry(@PathVariable("first")int first,@PathVariable("second")int second)
		{
			List<User>list=userService.betweenQry(first,second);
			if(list.isEmpty())
			{
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity(list,HttpStatus.OK);
			
		}
		
	
	 /* ********************** Logical And Or Expression*************/
		@GetMapping(value = "/andOr/{first}/{second}")
		public ResponseEntity<User>andOr(@PathVariable("first")int id,@PathVariable("second")String name)
		{
			List<User>list=userService.andOr(id,name);
			
			return new ResponseEntity(list,HttpStatus.OK);
		}
	 }
