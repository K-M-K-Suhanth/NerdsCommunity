package com.niit.testcases;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.ForumDiscussionDAO;
import com.niit.model.ForumDiscussion;


public class ForumDiscussionTestCase
{
	static ForumDiscussionDAO forumDiscussionDAO;
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		forumDiscussionDAO=(ForumDiscussionDAO)context.getBean("forumDiscussionDAO");
	}
	@Ignore
	@Test
	public void addForumCommentTestCase()
	{
		ForumDiscussion forumComment=new ForumDiscussion();
		forumComment.setForumId(2052);
		forumComment.setDiscussionDate(new java.util.Date());
		forumComment.setLoginname("kamesh");
		forumComment.setDiscussionText("goo for know a lot");
		
		assertTrue("Problem in Adding a Forum Comment",forumDiscussionDAO.addDiscussion(forumComment));
		
	}
	
	@Test
	public void listForumCommentTestCase()
	{
		List<ForumDiscussion> listComments=forumDiscussionDAO.getAllDiscussions(2002);
		assertTrue("Problem in Listing ForumsComments",listComments.size()>0);
		for(ForumDiscussion forumComment:listComments)
		{
			System.out.print(forumComment.getForumId()+":::");
			System.out.println(forumComment.getDiscussionText());
		}
	}
}
