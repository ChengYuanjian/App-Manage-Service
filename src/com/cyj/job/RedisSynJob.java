package com.cyj.job;

import com.cyj.util.LoggerUtil;
import com.cyj.util.RedisUtil;

/**
 * Synchronize the data from redis to database
 * @author chengyj
 *
 */
public class RedisSynJob {
	
	private static LoggerUtil logger = new LoggerUtil(RedisSynJob.class);
	
	public void execute()
	{
		logger.info("=========Synchronization start=========");
		RedisUtil.getInstance().getJedis();
		logger.info("=========Synchronization end=========");
	}

}
