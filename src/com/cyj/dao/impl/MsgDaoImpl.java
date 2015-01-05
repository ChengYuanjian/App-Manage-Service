package com.cyj.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cyj.base.dao.BaseDAO;
import com.cyj.bo.Msg;
import com.cyj.dao.MsgDao;
import com.cyj.util.LoggerUtil;

public class MsgDaoImpl extends BaseDAO implements MsgDao {

	private String namespace = "com.cyj.dao.MsgDao";

	private LoggerUtil logger = new LoggerUtil(MsgDaoImpl.class);

	@Override
	public boolean addMsg(Msg msg) {
		try {
			add(namespace + ".addMsg", msg);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	@Override
	public int deleteMsg(long msgid) {
		try {
			return update(namespace + ".deleteMsgById", msgid);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return 0;
		}	
	}

	@Override
	public Msg getMsgsById(long msgid) {
		try {
			return (Msg) queryForObject(namespace + ".getMsgById", msgid);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public List<Msg> getMsgsByPhone(long phone,int from, int to) {
		try {
			Map<String, Number> map = new HashMap<String, Number>();
			map.put("phone", phone);
			map.put("from", from);
			map.put("to", to);
			return (List<Msg>)queryForList(namespace+".getMsgByPhone", map);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public int updateMsg(Msg msg) {
		try {
			return update(namespace+".updateMsgById", msg);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return 0;
		}
	}

}
