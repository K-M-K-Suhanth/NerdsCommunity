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

import com.niit.dao.BlogCommentDAO;
import com.niit.dao.BlogDAO;
import com.niit.model.Blog;
import com.niit.model.BlogComment;


@RestController
public class BlogRestController
{	
	@Autowired
	BlogDAO blogDAO;
	
	@Autowired
	BlogCommentDAO blogCommentDAO;
	
	@GetMapping("/listBlogs")
	public ResponseEntity<List<Blog>> listBlogs()
	{
		List<Blog> listBlogs=blogDAO.listBlogs();
		
		if(listBlogs.size()>0)
		{
			return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value="/addBlog")
	public ResponseEntity<String> insertBlog(@RequestBody Blog blog,HttpSession session)
	{	
		String username=(String)session.getAttribute("loggedInUserID");
		blog.setLoginname(username);
		blog.setCreateDate(new java.util.Date());
		blog.setDislikes(0);
		blog.setLikes(0);
		blog.setStatus("NA");
		
		if(blogDAO.addBlog(blog))
		{
			return new ResponseEntity<String>("Success",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getBlog/{blogid}")
	public ResponseEntity<Blog> getBlog(@PathVariable("blogid")int blogid)
	{
		Blog blog=blogDAO.getBlog(blogid);
		if(blog!=null)
		{
			return new ResponseEntity<Blog>(blog,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Blog>(blog,HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/approveBlog/{blogid}")
	public ResponseEntity<Blog> approveBlog(@PathVariable("blogid")int blogid)
	{
		if(blogDAO.approveBlog(blogid))
		{
			return new ResponseEntity("Success",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity("Failure",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/rejectBlog/{blogid}")
	public ResponseEntity<Blog> rejectBlog(@PathVariable("blogid")int blogid)
	{
		if(blogDAO.rejectBlog(blogid))
		{
			return new ResponseEntity("Success",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity("Failure",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/incrementLikes/{blogid}")
	public ResponseEntity<Blog> incrementLikes(@PathVariable("blogid")int blogid)
	{
		if(blogDAO.incLikes(blogid))
		{
			return new ResponseEntity("Success",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity("Failure",HttpStatus.NOT_FOUND);
		}
	}
		
		@GetMapping("/incrementDisLikes/{blogid}")
		public ResponseEntity<Blog> incrementDisLikes(@PathVariable("blogid")int blogid)
		{
			if(blogDAO.disLikes(blogid))
			{
				return new ResponseEntity("Success",HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity("Failure",HttpStatus.NOT_FOUND);
			}
		}
		
		@GetMapping("/deleteBlog/{blogid}")
		public ResponseEntity<String> deleteBlog(@PathVariable("blogid")int blogid)
		{
			Blog blog=blogDAO.getBlog(blogid);
			
			if(blogDAO.deleteBlog(blog))
			{
				return new ResponseEntity<String>("Success",HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
			}
		}
		
		@PostMapping("/updateBlog")
		public ResponseEntity<String> updateBlog(@RequestBody Blog blog)
		{
			if(blogDAO.updateBlog(blog))
			{
				return new ResponseEntity<String>("Success",HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);	
			}
		}
		
		@GetMapping("/getAllComment/{blogid}")
		public ResponseEntity<List<BlogComment>> getAllBlogComments(@PathVariable("blogid")int blogid)
		{
			List<BlogComment> listBlogComments=blogCommentDAO.getAllComments(blogid);
			
			if(listBlogComments.size()>=0)
			{
				return new ResponseEntity<List<BlogComment>>(listBlogComments,HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<List<BlogComment>>(listBlogComments,HttpStatus.NOT_FOUND);
			}
		}
		
		@PostMapping("/insertComment")
		public ResponseEntity<String> insertComment(@RequestBody BlogComment comment)
		{
			comment.setCommentDate(new java.util.Date());
			if(blogCommentDAO.addComment(comment))
			{
				return new ResponseEntity("Success",HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity("Failure",HttpStatus.NOT_FOUND);	
			}
		}

		
		/*@PostMapping("/insertComment/{blogid}")
		public ResponseEntity<String> insertComment(@RequestParam("commenttext")String commenttext,@PathVariable("blogid")int blogid,HttpSession session)
		{
			BlogComment comment=new BlogComment();
			String username=(String)session.getAttribute("loggedInUserID");
			comment.setLoginname(username);
			comment.setCommentDate(new java.util.Date());
			comment.setBlogId(blogid);
			comment.setCommentText(commenttext);
			if(blogCommentDAO.addComment(comment))
			{
				return new ResponseEntity<String>("Success",HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);	
			}
		}*/
}