package com.jbk.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.view.jasperreports.JasperReportsCsvView;

import com.jbk.bean.User;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

@Repository
public class UserDaoImp implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void addUser(User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
	}

	public List<User> getUser() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<User> list = session.createCriteria(User.class).list();
		return list;
	}

	public User findById(int uid) {

		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.get(User.class, uid);
		return user;
	}

	@Override
	public List<User> findByName(String name) {
		Session sesn = sessionFactory.getCurrentSession();
		Query q = sesn.createQuery("FROM User where uname=:uname");
		q.setParameter("uname", name);
		List<User> user = q.list();
		return user;
	}

	public User update(User val, int uid) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.get(User.class, uid);
		/* user.setCountry(val.getC()); */
		user.setUname(val.getUname());
		user.setStatus(val.getStatus());
		user.setCreated_date(val.getCreated_date());
		user.setModified_date(val.getModified_date());
		user.setUphn(val.getUphn());
		session.update(user);
		return user;
	}

	public void delete(int uid) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		User user = findById(uid);
		session.delete(user);
	}

	@Override
	public User updatuname(User val, int uid) {
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.load(User.class, uid);
		user.setUid(val.getUid());
		return user;
	}

	@Override
	public User updateuname(User user, int uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findStatus(String status) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("FROM User U WHERE U.status = :status");
		q.setParameter("status", status);
		List<User> list = q.list();
		return list;
	}

	@Override
	public List<User> lessThan(int id) {
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.lt("uid", id));
		List<User> list = cr.list();
		return list;
	}

	@Override
	public List<User> likeName(String name) {
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.like("uname", name + "%"));
		List<User> list = cr.list();
		return list;
	}

	@Override
	public List<User> betweenQry(int first, int second) {
		Session sesn = sessionFactory.getCurrentSession();
		Criteria cr = sesn.createCriteria(User.class);
		cr.add(Restrictions.between("uid", first, second));
		List<User> list = cr.list();
		return list;

	}

	@Override
	public List<User> andOr(int id, String name) {
		Session sesn = sessionFactory.getCurrentSession();
		Criteria cr = sesn.createCriteria(User.class);
		Criterion gt = Restrictions.gt("uid", id);
		Criterion like = Restrictions.like("uname", name);
		LogicalExpression orexp = Restrictions.or(gt, like);
		cr.add(orexp);
		List<User> list = cr.list();

		return list;
	}

	@Override
	public List<User> paging(int from, int to) {
		Session sesn = sessionFactory.getCurrentSession();
		Criteria cr = sesn.createCriteria(User.class);
		cr.setFirstResult(from);
		cr.setMaxResults(to);
		List<User> list = cr.list();
		return list;
	}

	@Override
	public List<User> desc() {
		Session sesn = sessionFactory.getCurrentSession();
		Criteria cr = sesn.createCriteria(User.class);
		cr.addOrder(Order.desc("uid"));
		List<User> list = cr.list();
		return list;
	}

	@Override
	public List<User> rowCount() {
		Session sesn = sessionFactory.getCurrentSession();
		Criteria cr = sesn.createCriteria(User.class);
		cr.setProjection(Projections.rowCount());
		List list = cr.list();
		list.get(0);
		return list;
	}

	@Override
	public String generateReport(String format, HttpServletResponse response) {

		List<User> users = getUser();
		String destination = "E:\\Report";

		try {
			File file = ResourceUtils.getFile("classpath:Users.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(users);

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
			JasperExportManager.exportReportToXmlStream(jasperPrint, response.getOutputStream());
			JasperViewer jasperViewer=new JasperViewer(jasperPrint,false);
			jasperViewer.setVisible(true);
			
			if (format.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
			JasperExportManager.exportReportToPdfFile(jasperPrint, destination + "\\userList.pdf");
				destination = "File Generated at " + " " + destination;
				return destination;
			} else if (format.equalsIgnoreCase("html")) {
				JasperExportManager.exportReportToHtmlFile(jasperPrint, destination + "\\userList.html");
				destination = "HTML File Generated at " + " " + destination;
				return destination;
			} else {
				destination = "Wrong Format";
				return destination;
			}

		} catch (FileNotFoundException fe) {

			destination = "Folder not found at specified Drive";
		}

		catch (Exception e) {
			e.printStackTrace();
			destination = e.getMessage();
		}
		return destination;
	}

}
