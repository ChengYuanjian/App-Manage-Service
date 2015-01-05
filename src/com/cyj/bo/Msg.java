package com.cyj.bo;

import java.io.Serializable;

public class Msg implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long msgid;
	private String msgtype;
	private String msgcontent;
	private String msgtime;//yyyy-MM-dd hh:mm:ss
	private int collectnum=0;
	private int commentnum=0;
	private int transpondnum=0;
	private int agreenum=0;
	private int readnum=0;
	private String msglabel;
	private long pictureid;
	private long phone;
	public long getMsgid() {
		return msgid;
	}
	public void setMsgid(long msgid) {
		this.msgid = msgid;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	public String getMsgcontent() {
		return msgcontent;
	}
	public void setMsgcontent(String msgcontent) {
		this.msgcontent = msgcontent;
	}
	public String getMsgtime() {
		return msgtime;
	}
	public void setMsgtime(String msgtime) {
		this.msgtime = msgtime;
	}
	public int getCollectnum() {
		return collectnum;
	}
	public void setCollectnum(int collectnum) {
		this.collectnum = collectnum;
	}
	public int getCommentnum() {
		return commentnum;
	}
	public void setCommentnum(int commentnum) {
		this.commentnum = commentnum;
	}
	public int getTranspondnum() {
		return transpondnum;
	}
	public void setTranspondnum(int transpondnum) {
		this.transpondnum = transpondnum;
	}
	public int getAgreenum() {
		return agreenum;
	}
	public void setAgreenum(int agreenum) {
		this.agreenum = agreenum;
	}
	public int getReadnum() {
		return readnum;
	}
	public void setReadnum(int readnum) {
		this.readnum = readnum;
	}

	public String getMsglabel() {
		return msglabel;
	}
	public void setMsglabel(String msglabel) {
		this.msglabel = msglabel;
	}
	public long getPictureid() {
		return pictureid;
	}
	public void setPictureid(long pictureid) {
		this.pictureid = pictureid;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}

}
