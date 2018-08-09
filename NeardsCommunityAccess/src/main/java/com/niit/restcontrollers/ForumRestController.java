package com.niit.restcontrollers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.ForumDiscussionDAO;
import com.niit.dao.ForumDAO;
import com.niit.model.Forum;
import com.niit.model.ForumDiscussion;


@RestController
public class ForumRestController
{
	@Autowired
	ForumDAO forumDAO;
	
	@Autowired
	ForumDiscussionDAO forumDiscussionDAO;
	
	@GetMapping("/listForums")
	public ResponseEntity<List<Forum>> listForums()
	{
		List<Forum> listForums=forumDAO.listForums();
		
		if(listForums.size()>0)
		{
			return new ResponseEntity<List<Forum>>(listForums,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Forum>>(listForums,HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value="/addForum")
	public ResponseEntity<String> insertForum(@RequestBody Forum forum,HttpSession session)
	{	
		String username=(String)session.getAttribute("loggedInUserID");
		forum.setLoginname(username);
		forum.setCreateDate(new java.util.Date());
		forum.setStatus("NA");
		if(forumDAO.addForum(forum))
		{
			return new ResponseEntity<String>("Success",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getForum/{forumid}")
	public ResponseEntity<Forum> getForum(@PathVariable("forumid")int forumid)
	{
		Forum forum=forumDAO.getForum(forumid);
		if(forum!=null)
		{
			return new ResponseEntity<Forum>(forum,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Forum>(forum,HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/approveForum/{forumid}")
	public ResponseEntity<Forum> approveForum(@PathVariable("forumid")int forumid)
	{
		if(forumDAO.approveForum(forumid))
		{
			return new ResponseEntity("Success",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity("Failure",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/rejectForum/{forumid}")
	public ResponseEntity<Forum> rejectForum(@PathVariable("forumid")int forumid)
	{
		if(forumDAO.rejectForum(forumid))
		{
			return new ResponseEntity("Success",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity("Failure",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/deleteForum/{forumid}")
	public ResponseEntity<String> deleteForum(@PathVariable("forumid")int forumid)
	{
		Forum forum=forumDAO.getForum(forumid);
		
		if(forumDAO.deleteForum(forum))
		{
			return new ResponseEntity<String>("Success",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/updateForum")
	public ResponseEntity<String> updateForum(@RequestBody Forum forum)
	{
		if(forumDAO.updateForum(forum))
		{
			return new ResponseEntity<String>("Success",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);	
		}
	}
	
	@GetMapping("/getAllForumDiscussion/{forumid}")
	public ResponseEntity<List<ForumDiscussion>> getAllComments(@PathVariable("forumid")int forumid)
	{
		List<ForumDiscussion> listForumDiscussions=forumDiscussionDAO.getAllDiscussions(forumid);
		
		if(listForumDiscussions.size()>=0)
		{
			return new ResponseEntity<List<ForumDiscussion>>(listForumDiscussions,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<ForumDiscussion>>(listForumDiscussions,HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/insertForumDiscussion")
	public ResponseEntity<String> insertForumDiscussion(@RequestBody ForumDiscussion discussion,HttpSession session)
	{
		String username=(String)session.getAttribute("loggedInUserID");
		discussion.setLoginname(username);
		discussion.setDiscussionDate(new java.util.Date());
		if(forumDiscussionDAO.addDiscussion(discussion))
		{
			return new ResponseEntity<String>("Success",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);	
		}
	}
	
	
}
