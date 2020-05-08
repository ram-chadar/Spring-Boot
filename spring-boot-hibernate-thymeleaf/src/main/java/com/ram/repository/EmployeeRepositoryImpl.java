package com.ram.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ram.model.EmployeeEntity;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

	@Autowired
	private SessionFactory sf;

	@Override
	public List<EmployeeEntity> getAllEmployee() {

		Session session = sf.getCurrentSession();
		List<EmployeeEntity> list = session.createCriteria(EmployeeEntity.class).list();

		return list;
	}

	@Override
	public EmployeeEntity getEmployeeById(int id) {

		Session session = sf.getCurrentSession();
		EmployeeEntity employeeEntity = session.load(EmployeeEntity.class, id);

		return employeeEntity;
	}

	@Override
	public Serializable saveEmployee(EmployeeEntity entity) {
		Session session = sf.getCurrentSession();
		Serializable employeeEntity = session.save(entity);
		return employeeEntity;
	}

	@Override
	public void deleteEmployee(int id) {
		Session session = sf.getCurrentSession();
		Transaction transaction=session.beginTransaction();
		EmployeeEntity employeeEntity = session.load(EmployeeEntity.class, id);
		session.delete(employeeEntity);
		transaction.commit();
	}

	@Override
	public void updateEmployee(EmployeeEntity employeeEntity) {
		Session session = sf.getCurrentSession();
		Transaction transaction=session.beginTransaction();
		EmployeeEntity emp = session.load(EmployeeEntity.class, employeeEntity.getId());
		emp.setFirstName(employeeEntity.getFirstName());
		emp.setLastName(employeeEntity.getLastName());
		emp.setEmail(employeeEntity.getEmail());

		session.saveOrUpdate(emp);
		transaction.commit();
	}

}
