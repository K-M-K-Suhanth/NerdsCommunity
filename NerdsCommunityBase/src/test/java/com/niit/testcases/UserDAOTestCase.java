package com.niit.testcases;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.UserDAO;
import com.niit.model.UserDetail;




public class UserDAOTestCase
{
	static UserDAO userDAO;
	
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		userDAO=(UserDAO)context.getBean("userDAO");
		
	}
	@Ignore
	@Test
	public void registerUserTestCase()
	{
		UserDetail userDetail=new UserDetail();
		
		userDetail.setLoginname("suhanth");
		userDetail.setPassword("shiva999");
		userDetail.setUsername("suhanth");
		userDetail.setEmailid("suhanth@gmail.com");
		userDetail.setRole("ROLE_USER");
		userDetail.setMobile("955000");
		
		assertTrue("Problem in Registering the User:",userDAO.registerUser(userDetail));
	}
	
	@Test
	public void updateUserTestCase()
	{
		UserDetail userDetail=userDAO.getUser("suhanth");
		userDetail.setRole("ADMIN");
		
		assertTrue("Problem in Updating User:",userDAO.updateUser(userDetail));
	}
	/*@Ignore
	@Test
	public void checkLoginTestCase()
	{
		UserDetail userDetail=new UserDetail();
		userDetail.setLoginname("suhanth");
		userDetail.setPassword("shiva999");
		
		UserDetail userDetail1=userDAO.checkUser(loginname, password);
		
		assertNotNull("Problem in loginCheck",userDetail1);
		System.out.println("User Name:"+userDetail1.getUsername());
	}*/
}
