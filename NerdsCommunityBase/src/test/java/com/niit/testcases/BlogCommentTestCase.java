package com.niit.testcases;

import static org.junit.Assert.assertTrue;


import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.BlogCommentDAO;
import com.niit.model.BlogComment;


public class BlogCommentTestCase
{
static BlogCommentDAO blogCommentDAO;
@BeforeClass
public static void initialize()
{
	AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
	context.scan("com.niit");
	context.refresh();
	
	blogCommentDAO=(BlogCommentDAO)context.getBean("blogCommentDAO");
}

@Test
public void addBlogCommentTestCase()
{
	BlogComment blogComment=new BlogComment();
	blogComment.setBlogId(3002);
	blogComment.setCommentDate(new java.util.Date());
	blogComment.setLoginname("harisadu");
	blogComment.setCommentText("some what okey");
	
	assertTrue("Problem in Adding a Blog Comment",blogCommentDAO.addComment(blogComment));
	
}
@Ignore
@Test

public void listBlogCommentTestCase()
{
	List<BlogComment> listComments=blogCommentDAO.getAllComments(3002);
	assertTrue("Problem in Listing BlogsComments",listComments.size()>0);
	for(BlogComment blogComment:listComments)
	{
		System.out.print(blogComment.getBlogId()+":::");
		System.out.println(blogComment.getCommentText());
	}
}
}
