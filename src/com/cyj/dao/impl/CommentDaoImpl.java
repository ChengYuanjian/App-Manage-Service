package com.cyj.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cyj.base.dao.BaseDAO;
import com.cyj.bo.Comment;
import com.cyj.dao.CommentDao;
import com.cyj.util.LoggerUtil;

public class CommentDaoImpl extends BaseDAO implements CommentDao {

	private LoggerUtil logger = new LoggerUtil(CommentDaoImpl.class);
	private String namespace = "com.cyj.dao.CommentDao";

	@Override
	public boolean addComment(Comment comment) {
		try {
			add(namespace + ".addComment", comment);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	@Override
	public boolean deleteComment(long commentid) {
		try {
			update(namespace + ".deleteCommentById", commentid);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	@Override
	public List<Comment> getCommentByMsgId(long msgid, int from, int to) {
		try {
			Map<String, Number> map = new HashMap<String, Number>();
			map.put("msgid", msgid);
			map.put("from", from);
			map.put("to", to);

			return (List<Comment>) queryForList(namespace
					+ ".getCommentByMsgId", map);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

}
