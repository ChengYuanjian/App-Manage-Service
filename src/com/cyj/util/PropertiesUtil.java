package com.cyj.util;

import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertiesUtil {

	private static Logger logger = Logger.getLogger(PropertiesUtil.class);
	private static Properties properties = null;

	static {
		try {
			properties = new Properties();
			properties.load(PropertiesUtil.class.getResourceAsStream("/appconfig.properties"));
		} catch (Exception e) {
			logger.error("Failed to load appconfig.properties", e);
		}

	}

	public static String getProperties(String key) {
		if (properties != null)
			return properties.getProperty(key);
		return "";
	}
}
