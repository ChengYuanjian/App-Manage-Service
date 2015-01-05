package com.cyj.dao;

import java.util.List;

import com.cyj.bo.Msg;

public interface MsgDao {
	
	public boolean addMsg(Msg msg);
	public int deleteMsg(long msgid);
	public int updateMsg(Msg msg);
	public List<Msg> getMsgsByPhone(long phone, int from, int to);
	public Msg getMsgsById(long msgid);
}
