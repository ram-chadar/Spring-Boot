package com.jbk.dao;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;

import com.jbk.bean.User;

public interface UserDao {
	public void addUser(User user);
	public List<User> getUser();
	public User findById(int uid);
	public List<User> findByName(String name);
	public User update(User user, int uid);
	public User updateuname(User user, int uid);
	public void delete(int uid);
	public User updatuname(User val, int uid);
	
	public List<User> findStatus(String status);
	public List<User> lessThan(int id);
	public List<User> likeName(String name);
	public List<User> betweenQry(int first, int second);
	public List<User> andOr(int id, String name);
	
	public List<User> paging(int from, int to);
	public List<User> desc();
	public List<User> rowCount();
	
	public String generateReport(String format,HttpServletResponse response);

}