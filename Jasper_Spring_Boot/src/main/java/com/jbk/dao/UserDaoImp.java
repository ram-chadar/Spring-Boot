package com.jbk.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;
import com.jbk.model.User;
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

	public List<User> getUser() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<User> list = session.createCriteria(User.class).list();
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
		//	JasperViewer jasperViewer=new JasperViewer(jasperPrint,false);
			//jasperViewer.setVisible(true);   for this add awt window builder in eclipse or sts
			
			
			
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
