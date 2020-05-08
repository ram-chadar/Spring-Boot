package com.jbk.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jbk.bean.User;
import com.jbk.dao.UserDao;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

@Service
@Transactional
public class UserServiceImp implements UserService {
	@Autowired
	UserDao userDao;

	public List<User> getUser() {
		// TODO Auto-generated method stub
		return userDao.getUser();
	}

	public User findById(int id) {
		// TODO Auto-generated method stub
		return userDao.findById(id);
	}

	@Override
	public List<User> findByName(String name) {
		// TODO Auto-generated method stub
		return userDao.findByName(name);
	}

	public void createUser(User user) {
		// TODO Auto-generated method stub
		userDao.addUser(user);
	}

	public void deleteUserById(int id) {
		// TODO Auto-generated method stub
		userDao.delete(id);
	}
	/*
	 * @Override public User updatePartially(User user, int id) {
	 * userDao.updateCountry(user,id); return userDao.findById(id); }
	 */

	@Override
	public User update(User user, int id) {
		// TODO Auto-generated method stub
		return userDao.update(user, id);
	}

	@Override
	public List<User> findStatus(String status) {
		// TODO Auto-generated method stub
		return userDao.findStatus(status);
	}

	@Override
	public List<User> lessThan(int id) {
		return userDao.lessThan(id);

	}

	@Override
	public List<User> likeName(String name) {
		// TODO Auto-generated method stub
		return userDao.likeName(name);
	}

	@Override
	public List<User> betweenQry(int first, int second) {
		// TODO Auto-generated method stub
		return userDao.betweenQry(first, second);
	}

	@Override
	public List<User> andOr(int id, String name) {
		// TODO Auto-generated method stub
		return userDao.andOr(id, name);
	}

	@Override
	public List<User> paging(int from, int to) {
		// TODO Auto-generated method stub
		return userDao.paging(from, to);
	}

	@Override
	public List<User> desc() {
		// TODO Auto-generated method stub
		return userDao.desc();
	}

	@Override
	public List<User> rowCount() {
		// TODO Auto-generated method stub
		return userDao.rowCount();
	}

	@Override
	public String generateReport(String format,HttpServletResponse response) {
		// TODO Auto-generated method stub
		return userDao.generateReport(format,response);
	}

	@Override
	public String write_jxl() {
		WritableWorkbook workbook;
        try {
            workbook = Workbook.createWorkbook(new File("E:\\ABC.xls"));
            WritableSheet sheet = workbook.createSheet("student", 0);
            //coloumn
            Label label = new Label(0, 0, "Roll No");
            sheet.addCell(label);
            Label label1 = new Label(1, 0, "Name");
            sheet.addCell(label1);
            Label label2 = new Label(2, 0, "Mark");
            sheet.addCell(label2);
            
            //rows
            Number number1=new Number(0,1,101);
            sheet.addCell(number1);
            
            Label name1=new Label(1,1,"Ram");
            sheet.addCell(name1);
            
            Number number2=new Number(2,1,101);
            sheet.addCell(number2);
            
            
            
            workbook.write();
            
          
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

		return "Generated";
	}

}
