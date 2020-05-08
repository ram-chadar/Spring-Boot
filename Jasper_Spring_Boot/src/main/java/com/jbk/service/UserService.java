package com.jbk.service;

import javax.servlet.http.HttpServletResponse;

public interface UserService 
{
	
	public String generateReport(String format,HttpServletResponse response);
	
}
