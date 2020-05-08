package com.SpringBootApplication.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.SpringBootApplication.bean.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private SessionFactory factory;

	@Override
	public void saveEmployee(Employee employee) {
		System.out.println("dao val " + employee.getName());
		Session session = factory.getCurrentSession();
		session.save(employee);
	}

	@Override
	public List<Employee> getAllEmployee() {

		Session session = factory.getCurrentSession();
		Criteria crt = session.createCriteria(Employee.class);
		List<Employee> list = crt.list();
		return list;
	}

}
