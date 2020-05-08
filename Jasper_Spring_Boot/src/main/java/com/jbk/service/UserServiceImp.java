package com.jbk.service;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jbk.dao.UserDao;

@Service
@Transactional
public class UserServiceImp implements UserService {
	@Autowired
	UserDao userDao;

	@Override
	public String generateReport(String format, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return userDao.generateReport(format, response);
	}

}
