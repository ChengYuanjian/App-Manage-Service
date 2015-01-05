package com.cyj.bo;

import java.io.Serializable;

public class Comment implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private long commentid;
	private long msgid;
	private String content;
	private long phone;
	private int commentstatus;//0-未读;1-已读;-1-删除
	private String commenttime;//yyyy-MM-dd hh:mm:ss
	public long getCommentid() {
		return commentid;
	}
	public void setCommentid(long commentid) {
		this.commentid = commentid;
	}
	public long getMsgid() {
		return msgid;
	}
	public void setMsgid(long msgid) {
		this.msgid = msgid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public int getCommentstatus() {
		return commentstatus;
	}
	public void setCommentstatus(int commentstatus) {
		this.commentstatus = commentstatus;
	}
	public String getCommenttime() {
		return commenttime;
	}
	public void setCommenttime(String commenttime) {
		this.commenttime = commenttime;
	}

}
