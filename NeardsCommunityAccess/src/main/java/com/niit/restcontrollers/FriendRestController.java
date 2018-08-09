package com.niit.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.FriendDAO;
import com.niit.model.Friend;
import com.niit.model.UserDetail;

@RestController
public class FriendRestController 
{
@Autowired
FriendDAO friendDAO;

	@GetMapping("/showFriendList/{loginname}")
	public ResponseEntity<List<Friend>> showFriendList(@PathVariable("loginname")String loginname)
	{
		List<Friend> friendList=friendDAO.showFriendList(loginname);
		if(friendList.size()>0)
		{
			return new ResponseEntity<List<Friend>>(friendList,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Friend>>(friendList,HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/showPendingFriendRequest/{loginname}")
	public ResponseEntity<List<Friend>> showPendingFriendRequest(@PathVariable("loginname")String loginname)
	{
		List<Friend> pendingFriendList=friendDAO.showPendingFriendRequest(loginname);
		if(pendingFriendList.size()>0)
		{
			return new ResponseEntity<List<Friend>>(pendingFriendList,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Friend>>(pendingFriendList,HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/showSuggestedFriendList/{loginname}")
	public ResponseEntity<List<UserDetail>> showSuggestedFriendList(@PathVariable("loginname")String loginname)
	{
		List<UserDetail> showSuggestedFriendList=friendDAO.showSuggestedFriend(loginname);
		if(showSuggestedFriendList.size()>0)
		{
			return new ResponseEntity<List<UserDetail>>(showSuggestedFriendList,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<UserDetail>>(showSuggestedFriendList,HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/sendFriendRequest")
	public ResponseEntity<String> sendFriendRequest(@RequestBody Friend friend)
	{
		if(friendDAO.sendFriendRequest(friend))
		{
			return new ResponseEntity<String>("Success",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/deleteFriendRequest/{friendId}")
	public ResponseEntity<String> deleteFriendRequest(@PathVariable("friendId")int friendId)
	{
		if(friendDAO.deleteFriendRequest(friendId))
		{
			return new ResponseEntity<String>("Success",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/acceptFriendRequest/{friendId}")
	public ResponseEntity<String> acceptFriendRequest(@PathVariable("friendId")int friendId)
	{
		if(friendDAO.acceptFriendRequest(friendId))
		{
			return new ResponseEntity<String>("Success",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
		}
	}
}
