package com.niit.restcontrollers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.JobApplicationDAO;
import com.niit.model.JobApplication;


@RestController
public class JobApplicationRestController {

	@Autowired
	JobApplicationDAO jobApplicationDAO;
	
	@GetMapping("/applyjob/{jobid}")
	public ResponseEntity<String> applyjob(@PathVariable("jobid")int jobid,HttpSession session)
	{
		JobApplication jobApplication = new JobApplication();
		
		String username=(String)session.getAttribute("loggedInUserID");
		
		jobApplication.setApplieddate(new java.util.Date());
		jobApplication.setJobid(jobid);
		jobApplication.setUsername(username);
		if(jobApplicationDAO.applyjob(jobApplication))
		{
			return new ResponseEntity<String>("Success",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
		}
	}
}
