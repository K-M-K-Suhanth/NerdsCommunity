package com.niit.testcases;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.BlogDAO;
import com.niit.model.Blog;


public class BlogTestCase 
{
	static BlogDAO blogDAO;
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		blogDAO=(BlogDAO)context.getBean("blogDAO");
		
	}
	
	@Test
	public void addBlogTestCase()
	{
		Blog blog=new Blog();
		blog.setBlogName("oracle pl/sql");
		blog.setBlogContent("pl/sql Related Concept");
		blog.setCreateDate(new java.util.Date());
		blog.setLikes(1);
		blog.setDislikes(1);
		blog.setLoginname("mohit");
		blog.setStatus("A");
		assertTrue("Problem in Creating Blog",blogDAO.addBlog(blog));
	}
	@Ignore
	@Test
	public void deleteBlogTestCase()
	{
		Blog blog=blogDAO.getBlog(956);
		assertTrue("Problem in deletion of the Blog",blogDAO.deleteBlog(blog));
	}
	@Ignore
	@Test
	public void updateBlogTestCase()
	{
		Blog blog=blogDAO.getBlog(953);
		blog.setBlogName("JAVA SE");
		blog.setLikes(2);
		
		assertTrue("Problem in Blog Updation",blogDAO.updateBlog(blog));
	}
	@Ignore
	@Test
	public void listBlogTestCase()
	{
		List<Blog> listBlogs=blogDAO.listBlogs();
		assertTrue("Problem in Listing Blogs",listBlogs.size()>0);
		for(Blog blog:listBlogs)
		{
			System.out.print(blog.getBlogId()+":::");
			System.out.print(blog.getBlogName()+":::");
			System.out.println(blog.getBlogContent());
		}
	
	}
	@Ignore
	@Test
	public void approveBlogTestCase()
	{
		assertTrue("Problem in Approving Blog",blogDAO.approveBlog(953));
	}
	@Ignore
	@Test
	public void rejectBlogTestCase()
	{
		assertTrue("Problem in Rejecting Blog",blogDAO.rejectBlog(972));
	}
	@Ignore
	@Test
	public void incrementLikesBlogTestCase()
	{
		assertTrue("Problem in incrementing likes",blogDAO.incLikes(955));
	}
	@Ignore
	@Test
	public void disLikesBlogTestCase()
	{
		assertTrue("Problem in incrementing dislikes",blogDAO.disLikes(953));
	}
	
}
