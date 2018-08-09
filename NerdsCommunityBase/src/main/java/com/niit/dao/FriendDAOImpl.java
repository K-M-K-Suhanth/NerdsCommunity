package com.niit.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.Friend;
import com.niit.model.UserDetail;

@Repository("friendDAO")
@Transactional
public class FriendDAOImpl implements FriendDAO
{
	@Autowired 
	SessionFactory sessionFactory;
	public List<Friend> showFriendList(String loginname)
	{
		Session session=sessionFactory.openSession();
		Query query1=session.createQuery("from Friend where (friendloginname=:flogin or loginname=:mylogin) and status='A'");
		query1.setParameter("flogin",loginname);
		query1.setParameter("mylogin",loginname);
		List<Friend> friendList=(List<Friend>)query1.list();
		return friendList;
	}

	public List<Friend> showPendingFriendRequest(String loginname) 
	{
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Friend where friendloginname=:flogin and status='NA'");
		query.setParameter("flogin",loginname);
		List<Friend> pendingFriendRequest=query.list();
		return pendingFriendRequest;
	}

	public List<UserDetail> showSuggestedFriend(String loginname) 
	{
		String str="select loginname from UserDetail where ";
		str=str+"(loginname not in(select friendloginname from Friend where loginname='"+loginname+"'and status='A')";
		str=str+" and ";
		str=str+"loginname not in(select loginname from Friend where friendloginname='"+loginname+"'and status='A'))";
		str=str+" and ";
		str=str+" loginname!='"+loginname+"'";
		Session session=sessionFactory.openSession();
		Query query= session.createSQLQuery(str);
		List <String> username=(List<String>)query.list();
		ArrayList<UserDetail> suggestedFriends=new ArrayList<UserDetail>();
		int i=0;
		while(i<username.size())
		{
			UserDetail userDetail=(UserDetail)session.get(UserDetail.class,username.get(i));
			suggestedFriends.add(userDetail);
			i++;
		}
		return suggestedFriends;
	}

	public boolean sendFriendRequest(Friend friend)
	{
		try
		{
			sessionFactory.getCurrentSession().save(friend);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception arised"+e);
			return false;
		}
		
	}

	public boolean acceptFriendRequest(int friendId) 
	{
		try
		{
			Session session=sessionFactory.openSession();
			Friend friend=(Friend)session.get(Friend.class, friendId);
			friend.setStatus("A");
			session.update(friend);
			session.close();
			sessionFactory.getCurrentSession().update(friend);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception arised"+e);
			return false;
		}
	}

	public boolean deleteFriendRequest(int friendId)
	{
		try
		{
			Session session=sessionFactory.openSession();
			Friend friend=(Friend)session.get(Friend.class, friendId);
			sessionFactory.getCurrentSession().delete(friend);
			session.close();
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception arised"+e);
			return false;
		}
	}

}
