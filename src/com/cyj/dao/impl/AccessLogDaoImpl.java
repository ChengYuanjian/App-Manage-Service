package com.cyj.dao.impl;

import com.cyj.base.dao.BaseDAO;
import com.cyj.bo.AccessLog;
import com.cyj.dao.AccessLogDao;
import com.cyj.util.LoggerUtil;

public class AccessLogDaoImpl extends BaseDAO implements AccessLogDao {
	
	private LoggerUtil logger = new LoggerUtil(AccessLogDaoImpl.class);
	private String namespace = "com.cyj.dao.AccessLogDao";

	@Override
	public boolean addAccessLog(AccessLog alog) {
		try {
			add(namespace+".addAccessLog", alog);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

}
