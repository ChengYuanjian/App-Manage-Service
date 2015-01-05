package com.cyj.base.exception;

import com.cyj.util.PropertiesUtil;

public class SystemException extends Exception {
	private static final String EXPINFO = "<-[System Excetion]->";
	private String msg;
	private int code = 0;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SystemException(String msg, Throwable t) {
		super(msg, t);
		this.msg = msg;
	}
	
	public SystemException(int code) {
		super(PropertiesUtil.getProperties(String.valueOf(code)));
		this.code = code;
		this.msg = PropertiesUtil.getProperties(String.valueOf(code));
	}
	
	public SystemException(Throwable t) {
		super(t);
		this.msg = t.getLocalizedMessage();
	}

	@Override
	public String getMessage() {
		return msg;
	}

	@Override
	public String toString() {
		return EXPINFO +msg + super.toString();
	}
	
	public int getCode()
	{
		return code;
	}

}
