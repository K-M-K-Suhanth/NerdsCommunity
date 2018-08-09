package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.Blog;
import com.niit.model.BlogComment;


@Repository("blogCommentDAO")
@Transactional
public class BlogCommentDAOImpl implements BlogCommentDAO
{
	@Autowired
	SessionFactory sessionFactory;
	public boolean addComment(BlogComment blogComment) 
	{
		try
		{
			sessionFactory.getCurrentSession().save(blogComment);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised"+e);
			return false;
		}
		
	}

	public boolean deleteComment(BlogComment blogComment)
	{
		try
		{
			sessionFactory.getCurrentSession().delete(blogComment);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised"+e);
			return false;
		}	
	}

	public List<BlogComment> getAllComments(int blogId)
	{
	try
	{
	Session session=sessionFactory.openSession();
	List<BlogComment> listBlogComments=(List<BlogComment>)session.createQuery("from BlogComment where blogid=:myblogid ")
			.setParameter("myblogid", blogId).list();
	
	return listBlogComments;
	}
	catch(Exception e)
	{
		System.out.println("Exception Arised"+e);
		return null;
	}
	}

}
