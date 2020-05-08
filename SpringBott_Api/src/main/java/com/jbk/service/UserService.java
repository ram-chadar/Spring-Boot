package com.jbk.service;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import com.jbk.bean.User;

public interface UserService {
	
	public void createUser(User user);
	public List<User> getUser();
	public User findById(int id);
	public List<User> findByName(String name);
	public User update(User user, int id);
	public void deleteUserById(int id);
	
	public List<User> findStatus(String status);
	public List<User> lessThan(int id);
	public List<User> likeName(String name);
	public List<User> betweenQry(int first, int second);
	public List<User> andOr(int id, String name);
	
	public List<User> paging(int from, int to);
	public List<User> desc();
	public List<User> rowCount();
	
	public String generateReport(String format,HttpServletResponse response);
	
	public String write_jxl();
	

}
