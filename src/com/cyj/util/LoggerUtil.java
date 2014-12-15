package com.cyj.util;

import org.apache.log4j.Logger;

public class LoggerUtil {

	private Class<?> clazz;

	public LoggerUtil(Class<?> clazz) {
		this.clazz = clazz;
	}

	public void debug(int retCode, Exception e) {
		Logger.getLogger(clazz).debug(
				PropertiesUtil.getProperties(String.valueOf(retCode)), e);
	}

	public void debug(int retCode) {
		Logger.getLogger(clazz).debug(
				PropertiesUtil.getProperties(String.valueOf(retCode)));
	}

	public void debug(String msg) {
		Logger.getLogger(clazz).debug(msg);
	}

	public void debug(String msg, Exception e) {
		Logger.getLogger(clazz).debug(msg, e);
	}

	public void info(int retCode, Exception e) {
		Logger.getLogger(clazz).info(
				PropertiesUtil.getProperties(String.valueOf(retCode)), e);
	}

	public void info(int retCode) {
		Logger.getLogger(clazz).info(
				PropertiesUtil.getProperties(String.valueOf(retCode)));
	}

	public void info(String msg, Exception e) {
		Logger.getLogger(clazz).info(msg, e);
	}

	public void info(String msg) {
		Logger.getLogger(clazz).info(msg);
	}

	public void warn(int retCode, Exception e) {
		Logger.getLogger(clazz).warn(
				PropertiesUtil.getProperties(String.valueOf(retCode)), e);
	}

	public void error(int retCode, Exception e) {
		Logger.getLogger(clazz).error(
				PropertiesUtil.getProperties(String.valueOf(retCode)), e);
	}

	public void error(String msg, Exception e) {
		Logger.getLogger(clazz).error(msg, e);
	}

	public void fatal(int retCode, Exception e) {
		Logger.getLogger(clazz).fatal(
				PropertiesUtil.getProperties(String.valueOf(retCode)), e);
	}

	public void fatal(String msg, Exception e) {
		Logger.getLogger(clazz).fatal(msg, e);
	}

}
