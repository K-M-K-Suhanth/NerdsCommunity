package com.niit.dao;

import java.util.List;

import com.niit.model.ForumDiscussion;


public interface ForumDiscussionDAO
{
	public boolean addDiscussion(ForumDiscussion forumDiscussion);
	public boolean deleteDiscussion(ForumDiscussion forumDiscussion);
	public List<ForumDiscussion> getAllDiscussions(int forumId);
}
