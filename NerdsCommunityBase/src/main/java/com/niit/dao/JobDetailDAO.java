package com.niit.dao;

import java.util.List;

import com.niit.model.JobDetail;
public interface JobDetailDAO {

	public boolean addjob(JobDetail jobDetail);
	public boolean updatejob(JobDetail jobDetail);
	public boolean deletejob(int jobid);
	public JobDetail getjob(int jobid);
	public List<JobDetail> listjobs();
	
}

