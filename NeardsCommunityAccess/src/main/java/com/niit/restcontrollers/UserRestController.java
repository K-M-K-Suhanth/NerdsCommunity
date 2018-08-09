package com.niit.restcontrollers;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.UserDAO;
import com.niit.model.UserDetail;


@RestController
public class UserRestController 
{
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	HttpSession httpSession;
	
	@PostMapping("/registerUser")
	public ResponseEntity<String> registerUser(@RequestBody UserDetail userDetail)
	{
		userDetail.setRole("USER");
		if(userDAO.registerUser(userDetail))
		{
			return new ResponseEntity<String>("Success",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
		}
	}
			
	@PostMapping("/checkLogin")
	public ResponseEntity<UserDetail> checkLogin(@RequestBody UserDetail userDetail,HttpSession session)
	{
		String myloginname=userDetail.getLoginname();
		String mypassword=userDetail.getPassword();
		session.setAttribute("loggedInUserID", myloginname);
		UserDetail tempUserDetail=userDAO.checkUser(myloginname, mypassword);
		if(tempUserDetail!=null)
		{
			session.setAttribute("userDetail", tempUserDetail);
			return new ResponseEntity<UserDetail>(tempUserDetail,HttpStatus.OK);
			
		}
		else
		{
			return new ResponseEntity<UserDetail>(tempUserDetail,HttpStatus.NOT_FOUND);
		}
	}

	
	@GetMapping("/getUser/{userid}")
	public ResponseEntity<UserDetail> getUserDetail(@PathVariable("userid")int userid)
	{
		UserDetail user=userDAO.getUser(userid);
		if(user!=null)
		{
			return new ResponseEntity<UserDetail>(user,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<UserDetail>(user,HttpStatus.NOT_FOUND);
		}
	}
}
