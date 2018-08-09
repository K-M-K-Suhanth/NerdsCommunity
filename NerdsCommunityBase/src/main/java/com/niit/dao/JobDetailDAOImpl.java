package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.JobDetail;


@Repository("jobDetailDAO")
@Transactional
public class JobDetailDAOImpl implements JobDetailDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	public boolean addjob(JobDetail jobDetail) {
		try
		{
			sessionFactory.getCurrentSession().save(jobDetail);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arissed:" +e);
			return false;
		}
	}

	public boolean updatejob(JobDetail jobDetail) {
		try
		{
			sessionFactory.getCurrentSession().update(jobDetail);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arissed:" +e);
			return false;
		}
	}

	public boolean deletejob(int jobid) {
		try
		{
			Session session=sessionFactory.openSession();
			JobDetail jobDetail=(JobDetail)session.get(JobDetail.class,jobid);
			sessionFactory.getCurrentSession().delete(jobDetail);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arissed:" +e);
			return false;
		}
	}

	public List<JobDetail> listjobs() {
		try
		{
			Session session=sessionFactory.openSession();
			Query query=session.createQuery("from JobDetail");
			List<JobDetail> listjobs=query.list();
			session.close();
			return listjobs;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arissed:" +e);
			return null;
		}
	}

	public JobDetail getjob(int jobid) {
		try
		{
			Session session=sessionFactory.openSession();
			JobDetail jobDetail = (JobDetail)session.get(JobDetail.class,jobid);
			session.close();
			return jobDetail;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arissed:" +e);
			return null;
		}
	}

}

