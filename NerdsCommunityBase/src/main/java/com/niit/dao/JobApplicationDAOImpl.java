package com.niit.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.JobApplication;


@Repository("jobApplicationDAO")
@Transactional
public class JobApplicationDAOImpl implements JobApplicationDAO {

	@Autowired
	SessionFactory sessionFactory;
	public boolean applyjob(JobApplication jobApplication) {
		try
		{
			
			jobApplication.setStatus("Applied");
			sessionFactory.getCurrentSession().save(jobApplication);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised:" +e);
			return false;
		}
	}

}
