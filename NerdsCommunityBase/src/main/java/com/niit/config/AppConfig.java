package com.niit.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.model.Blog;
import com.niit.model.BlogComment;
import com.niit.model.Forum;
import com.niit.model.ForumDiscussion;
import com.niit.model.Friend;
import com.niit.model.JobApplication;
import com.niit.model.JobDetail;
import com.niit.model.Message;
import com.niit.model.OutputMessage;
import com.niit.model.ProfilePicture;
import com.niit.model.UserDetail;

@ComponentScan(basePackages="com.niit.*")
@Configuration
@EnableTransactionManagement
public class AppConfig {

	@Bean(name="dataSource")
	public DataSource getDataSource()
	{
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@suhanth:1521:XE");
		dataSource.setUsername("suhanth");
		dataSource.setPassword("suhanth1234");
		
		System.out.println("---Data Source Created---");
		return dataSource;
	}
	
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory()
	{
		Properties hibernateProp=new Properties();
		
		hibernateProp.setProperty("hibernate.hbm2ddl.auto", "update");
		hibernateProp.put("hibernate.dialect","org.hibernate.dialect.Oracle10gDialect");
		
		LocalSessionFactoryBuilder factoryBuilder=new LocalSessionFactoryBuilder(getDataSource());
		factoryBuilder.addProperties(hibernateProp);
		factoryBuilder.addAnnotatedClass(Blog.class);
		factoryBuilder.addAnnotatedClass(BlogComment.class);
		factoryBuilder.addAnnotatedClass(Forum.class);
		factoryBuilder.addAnnotatedClass(ForumDiscussion.class);
		factoryBuilder.addAnnotatedClass(Friend.class);
		factoryBuilder.addAnnotatedClass(JobApplication.class);
		factoryBuilder.addAnnotatedClass(JobDetail.class);
		factoryBuilder.addAnnotatedClass(Message.class);
		factoryBuilder.addAnnotatedClass(OutputMessage.class);
		factoryBuilder.addAnnotatedClass(ProfilePicture.class);
		factoryBuilder.addAnnotatedClass(UserDetail.class);
				
		System.out.println("---Creating SessionFactory Bean---");
		return factoryBuilder.buildSessionFactory();
	}
		
	@Bean(name="txManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory)
	{
		System.out.println("---Transaction Manager---");
		return new HibernateTransactionManager(sessionFactory);
	}
}
