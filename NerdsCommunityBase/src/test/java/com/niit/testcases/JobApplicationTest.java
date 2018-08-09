package com.niit.testcases;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.JobApplicationDAO;
import com.niit.model.JobApplication;

public class JobApplicationTest {

	@Autowired
	static JobApplicationDAO jobApplicationDAO;
	
	@BeforeClass
	public static void init()
	{
		AnnotationConfigApplicationContext context= new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		jobApplicationDAO=(JobApplicationDAO)context.getBean("jobApplicationDAO"); 
	}
	
	@Test
	public void applyjob()
	{
		JobApplication jobApplication = new JobApplication();
		
		jobApplication.setJobid(1);
		jobApplication.setUsername("vdjc");
		jobApplication.setStatus("Applied");
		
		assertTrue("problem in adding job",jobApplicationDAO.applyjob(jobApplication));
	}
	
}
