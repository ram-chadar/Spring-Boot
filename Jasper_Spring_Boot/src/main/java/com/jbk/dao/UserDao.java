package com.jbk.dao;

import javax.servlet.http.HttpServletResponse;

public interface UserDao {
	
	public String generateReport(String format,HttpServletResponse response);

}