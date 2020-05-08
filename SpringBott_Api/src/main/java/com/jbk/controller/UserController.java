package com.jbk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.jbk.bean.User;
import com.jbk.service.UserService;

@CrossOrigin
@RestController
@RequestMapping(value={"/user"})
public class UserController {
	@Autowired
	UserService userService;
	
	
	@RequestMapping(value = "/msg/{msg}")
	public String msg(@PathVariable("msg")String msg)
	{
		return msg+" "+"JBK";
	}
	

    @GetMapping(value = "/{uid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserById(@PathVariable("uid") int uid) {
        System.out.println("Fetching User with id " + uid);
        User user = userService.findById(uid);
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    
    @GetMapping(value = "/byname/{uname}")
    public ResponseEntity<User>getUserByName(@PathVariable("uname")String name)
    {
    List<User> user=userService.findByName(name);
    	if(user.isEmpty())
    	{
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);

    	}
		return new ResponseEntity( user,HttpStatus.OK);
    }
    
    
	 @PostMapping(value="/save",headers="Accept=application/json")
	 public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder){
	     System.out.println("Creating User "+user.getUname());
	     userService.createUser(user);
	     HttpHeaders headers = new HttpHeaders();
	     headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getUid()).toUri());
	     return new ResponseEntity<Void>(headers, HttpStatus.CREATED); 
	 }

	 @GetMapping(value="/get", headers="Accept=application/json")
	 public List<User> getAllUser() {	 
	  List<User> tasks=userService.getUser();
	  return tasks;
	
	 }

	@PutMapping(value="/update", headers="Accept=application/json")
	public ResponseEntity<String> updateUser(@RequestBody User currentUser)
	{
		System.out.println("sd");
	User user = userService.findById(currentUser.getUid());
	if (user==null) {
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
	userService.update(currentUser, currentUser.getUid());
	return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}", headers ="Accept=application/json")
	public ResponseEntity<User> deleteUser(@PathVariable("id") int uid){
		User user = userService.findById(uid);
		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		userService.deleteUserById(uid);
		return new ResponseEntity<User>(HttpStatus.MOVED_PERMANENTLY);
	}
	
	/*@PatchMapping(value="/{id}", headers="Accept=application/json")
	public ResponseEntity<User> updateUserPartially(@PathVariable("id") int id, @RequestBody User currentUser){
		User user = userService.findById(id);
		if(user ==null){
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		
		User usr =	userService.updatePartially(currentUser, id);
		return new ResponseEntity<User>(usr, HttpStatus.OK);*/
	
	@GetMapping(value = "/status/{status}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getStatus(@PathVariable("status") String status)
	{
		List<User> user=userService.findStatus(status);
		if(user==null)
		{
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(user,HttpStatus.OK);
		
	}
	
	
	
	}

