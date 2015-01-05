package com.cyj.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyj.base.exception.BizLogicException;
import com.cyj.bo.Comment;
import com.cyj.bo.RetInfo;
import com.cyj.dao.CommentDao;
import com.cyj.util.JSONUtil;
import com.cyj.util.LoggerUtil;

@Controller
@RequestMapping("/comment")
public class CommentController {

	private LoggerUtil logger = new LoggerUtil(CommentController.class);

	@Autowired
	private CommentDao commentDao;

	public CommentDao getCommentDao() {
		return commentDao;
	}

	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	@RequestMapping(value = "/c", method = RequestMethod.POST)
	public @ResponseBody
	RetInfo addComment(@RequestBody
	Map<String, Object> map) {
		try {
			Comment comment = (Comment) JSONUtil.map2bean(map, Comment.class);
			if (commentDao.addComment(comment))
				return new RetInfo(1);
			return new RetInfo(-31000);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new RetInfo(0);
		}
	}

	@RequestMapping(value = "/d/{commentid}", method = RequestMethod.DELETE)
	public @ResponseBody
	RetInfo deleteComment(@PathVariable
	long commentid) {
		try {
			if (commentDao.deleteComment(commentid))
				return new RetInfo(1);
			else
				return new RetInfo(-33000);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new RetInfo(0);
		}
	}

	@RequestMapping(value = "/r/{msgid}/{from}/{to}", method = RequestMethod.GET)
	public @ResponseBody
	Object getCommentByMsgId(@PathVariable
	long msgid, @PathVariable
	int from, @PathVariable
	int to) {
		try {
			if (to < from)
				throw new BizLogicException(-40001);
			List<Comment> comments = commentDao.getCommentByMsgId(msgid, from,
					to);
			if (CollectionUtils.isEmpty(comments))
				return new RetInfo(-30000);
			return comments;
		} catch (BizLogicException e) {
			logger.error(e.getMessage(), e);
			return new RetInfo(e.getCode());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new RetInfo(0);
		}
	}

}
