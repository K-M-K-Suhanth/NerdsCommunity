package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.Forum;

@Repository("forumDAO")
@Transactional
public class ForumDAOImpl implements ForumDAO
{
	@Autowired
	SessionFactory sessionFactory;
	public boolean addForum(Forum forum)
	{
		try
		{
			sessionFactory.getCurrentSession().save(forum);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised:"+e);
			return false;
		}
	}

	public boolean deleteForum(Forum forum) 
	{
		
		try
		{
			sessionFactory.getCurrentSession().delete(forum);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised:"+e);
			return false;
		}
	}

	public boolean updateForum(Forum forum) 
	{
		
		try
		{
			sessionFactory.getCurrentSession().update(forum);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised:"+e);
			return false;
		}
	}

	public List<Forum> listForums() 
	{
	
		try
		{
			Session session=sessionFactory.openSession();
			Query query=session.createQuery("from Forum");
			List<Forum> listForums=query.list();
			session.close();
			return listForums;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised:"+e);
			return null;
		}
	}

	public boolean approveForum(int forumId) 
	{
		try 
		{
			Forum forum=this.getForum(forumId);
			forum.setStatus("A");
			sessionFactory.getCurrentSession().update(forum);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised:"+e);
			return false;
		}
	}

	public boolean rejectForum(int forumId)
	{
		
		try 
		{
			Forum forum=this.getForum(forumId);
			forum.setStatus("NA");
			sessionFactory.getCurrentSession().update(forum);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised:"+e);
			return false;
		}
	}

	public Forum getForum(int forumId) 
	{
		try
		{
			Session session=sessionFactory.openSession();
			Forum forum=(Forum)session.get(Forum.class,forumId);
			session.close();
			return forum;
		}
		catch(Exception e)
		{
			System.out.println("Exception arised:"+e);
			return null;
		}
		
	}

	
}
