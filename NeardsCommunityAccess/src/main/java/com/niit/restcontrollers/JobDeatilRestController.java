package com.niit.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.JobDetailDAO;
import com.niit.model.JobDetail;


@RestController
public class JobDeatilRestController {

	@Autowired
	JobDetailDAO jobDetailDAO;
	
	@PostMapping("/addJob")
	public ResponseEntity<String> addjob(@RequestBody JobDetail jobDetail)
	{

		jobDetail.setPosteddate(new java.util.Date());
		if(jobDetailDAO.addjob(jobDetail))
		{
			return new ResponseEntity<String>("Success",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/updateJob")
	public ResponseEntity<String> update(@RequestBody JobDetail jobDetail)
	{
		if(jobDetailDAO.updatejob(jobDetail))
		{
			return new ResponseEntity<String>("Success",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/deleteJob/{jobid}")
	public ResponseEntity<String> delete(@PathVariable("jobid")int jobid )
	{
		if(jobDetailDAO.deletejob(jobid))
		{
			return new ResponseEntity<String>("Success",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/showJobs")
	public ResponseEntity<List<JobDetail>> addjob1()
	{
		List<JobDetail> listjobs=jobDetailDAO.listjobs();
		
		if(listjobs.size()>0)
		{
			return new ResponseEntity<List<JobDetail>>(listjobs,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<JobDetail>>(listjobs,HttpStatus.NOT_FOUND);
		}
	}
}
