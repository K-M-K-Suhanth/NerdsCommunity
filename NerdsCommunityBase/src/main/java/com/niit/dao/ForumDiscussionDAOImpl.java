package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.ForumDiscussion;

@Repository("forumDiscussionDAO")
@Transactional
public class ForumDiscussionDAOImpl implements ForumDiscussionDAO
{
	@Autowired
	SessionFactory sessionFactory;
	public boolean addDiscussion(ForumDiscussion forumDiscussion) 
	{
		try
		{
			sessionFactory.getCurrentSession().save(forumDiscussion);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised"+e);
			return false;
		}
}
	public boolean deleteDiscussion(ForumDiscussion forumDiscussion)
	{
		try
		{
			sessionFactory.getCurrentSession().delete(forumDiscussion);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised"+e);
			return false;
		}	
	}

	public List<ForumDiscussion> getAllDiscussions(int forumId)
	{
	try
	{
	Session session=sessionFactory.openSession();
	List<ForumDiscussion> listForumDiscussions=(List<ForumDiscussion>)session.createQuery("from ForumDiscussion where forumid=:myforumid ")
			.setParameter("myforumid", forumId).list();
	
	return listForumDiscussions;
	}
	catch(Exception e)
	{
		System.out.println("Exception Arised"+e);
		return null;
	}
	}
}