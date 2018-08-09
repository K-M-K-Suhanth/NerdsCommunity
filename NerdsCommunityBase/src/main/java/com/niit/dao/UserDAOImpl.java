package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.ForumDiscussion;
import com.niit.model.UserDetail;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO
{
	@Autowired
	SessionFactory sessionFactory;
	public boolean registerUser(UserDetail userDetail)
	{
		try 
		{
			sessionFactory.getCurrentSession().save(userDetail);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised"+e);
			return false;
		}
	}

	public boolean updateUser(UserDetail userDetail)
	{
		try 
		{
			sessionFactory.getCurrentSession().update(userDetail);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised"+e);
			return false;
		}
	}

	public UserDetail getUser(int userId)
	{
		Session session=sessionFactory.openSession();
		UserDetail userDetail=(UserDetail)session.get(UserDetail.class,userId);
		session.close();
		return userDetail;
	}

	public UserDetail getUser(String loginname) 
	{
		Session session=sessionFactory.openSession();
		UserDetail userDetail=(UserDetail)session.get(UserDetail.class,loginname);
		session.close();
		return userDetail;
	}
	
	public UserDetail checkUser(String loginname,String password)
	{
		//Session session=sessionFactory.openSession();
		System.out.println("name"+loginname);
		System.out.println("pass"+password);
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from UserDetail where loginname=:myloginname and password=:mypassword");
		query.setParameter("myloginname", loginname);
		query.setParameter("mypassword", password);
		List<UserDetail> listUserDetail=query.list();
		UserDetail userDetail1=listUserDetail.get(0);
		session.close();
		return userDetail1;

			
	}
	
}

	
	/*String hql = "from UserDetail where loginname= '" + loginname + "' and " + " password ='" + password+"'";
	Query query = sessionFactory.getCurrentSession().createQuery(hql);
	@SuppressWarnings("unchecked")
	List<UserDetail> list = (List<UserDetail>) query.list();
	UserDetail userDetail = (UserDetail)session.
	createQuery("from UserDetail where (loginname=:myloginname && password=:mypassword) ")
			.setParameter("myloginname", loginname).setParameter("mypassword", password);
			
	session.close();
	if(list.get(0)!=null)	
	{
	return true;
	}
	return false;
}
catch(Exception e)
{
	System.out.println("problem");
	return false;
}*/