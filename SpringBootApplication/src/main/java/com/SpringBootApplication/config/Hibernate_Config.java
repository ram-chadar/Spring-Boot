package com.SpringBootApplication.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@Configuration
@EnableTransactionManagement
public class Hibernate_Config {
	
	@Value("${db.driver}")
	private String driver;
	
	@Value("${db.url}")
	private String url;
	
	@Value("${db.username}")
	private String username;
	
	@Value("${db.password}")
	private String password;
	
	@Value("${hibernate.dialect}")
	private String dialect;
	
	@Value("${hibernate.show_sql}")
	private String show_sql;
	
	@Value("${hibernate.hbm2ddl.auto}")
	private String hbm2ddl;
	
	@Value("${entitymanager.packagesToScan}")
	private String packagesToScan;
	
	@Bean
	public DataSource mySqldataSource()
	{
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
		
	}
	
	/*
	 * public Properties hibernateProperties() { Properties hibernateProperties=new
	 * Properties(); hibernateProperties.put("hibernate.dialect", dialect);
	 * hibernateProperties.put("hibernate.show_sql", show_sql);
	 * hibernateProperties.put("hibernate.hbm2ddl.auto", hbm2ddl); return
	 * hibernateProperties;
	 * 
	 * }
	 */
	@Bean
	public LocalSessionFactoryBean sessionfactory()
	{
		LocalSessionFactoryBean  localSessionFactoryBean=new LocalSessionFactoryBean();
		localSessionFactoryBean.setDataSource(mySqldataSource());
		Properties hibernateProperties=new Properties();
		hibernateProperties.put("hibernate.dialect", dialect);
		hibernateProperties.put("hibernate.show_sql", show_sql);
		hibernateProperties.put("hibernate.hbm2ddl.auto", hbm2ddl);
		localSessionFactoryBean.setHibernateProperties(hibernateProperties);
		localSessionFactoryBean.setPackagesToScan(packagesToScan);
		return localSessionFactoryBean;
		
	}
	
	@Bean
	public HibernateTransactionManager transaction()
	{
		HibernateTransactionManager hibernateTransactionManager=new HibernateTransactionManager();
		hibernateTransactionManager.setSessionFactory(sessionfactory().getObject());
		return hibernateTransactionManager;
		
	}
	

}
