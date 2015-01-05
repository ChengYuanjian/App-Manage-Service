package com.cyj.dao;

import java.util.List;

import com.cyj.bo.Comment;

public interface CommentDao {

	public boolean addComment(Comment comment);

	public boolean deleteComment(long commentid);

	public List<Comment> getCommentByMsgId(long msgid, int from, int to);
}
