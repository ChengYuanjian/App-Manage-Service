package com.cyj.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {

	private static JedisPool pool = null;
	private int maxTotal = Integer.parseInt(PropertiesUtil.getProperties("redis.maxTotal"));
	private int maxIdle = Integer.parseInt(PropertiesUtil.getProperties("redis.maxIdle"));
	private long maxWaitMillis = Long.parseLong(PropertiesUtil.getProperties("redis.maxWaitMillis"));
	private String host = PropertiesUtil.getProperties("redis.host");
	private int port = Integer.parseInt(PropertiesUtil.getProperties("redis.port"));

	private static RedisUtil instance = null;
	
	private static LoggerUtil logger = new LoggerUtil(RedisUtil.class);

	public static RedisUtil getInstance() {
		if (instance == null)
			instance = new RedisUtil();
		return instance;
	}

	private RedisUtil() {
		try {
			if (pool == null) {
				JedisPoolConfig config = new JedisPoolConfig();
				config.setMaxTotal(maxTotal);
				config.setMaxIdle(maxIdle);
				config.setMaxWaitMillis(maxWaitMillis);

				pool = new JedisPool(config, host, port);
			}
		} catch (Exception e) {
			logger.fatal(10000, e);
		}
	}

	public Jedis getJedis() {
		if (pool != null)
			return pool.getResource();
		return null;
	}

	public void returnResource(Jedis redis) {
		if (redis != null) {
			pool.returnResource(redis);
		}
	}

}
