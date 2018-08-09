package com.niit.testcases;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.JobDetailDAO;
import com.niit.model.JobDetail;

public class JobDetailTestCase {

	@Autowired
	static JobDetailDAO jobDetailDAO;
	
	@BeforeClass
	public static void init()
	{
		AnnotationConfigApplicationContext context= new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		jobDetailDAO=(JobDetailDAO)context.getBean("jobDetailDAO"); 
	}
	
	@Test
	public void addjob()
	{
		JobDetail jobDetail = new JobDetail();
		
		jobDetail.setCompany("ksjgfdj");
		jobDetail.setPosition("vdjc");
		
		assertTrue("problem in adding job",jobDetailDAO.addjob(jobDetail));
	}
}
