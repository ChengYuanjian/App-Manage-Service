package com.cyj.base.exception;

import com.cyj.util.PropertiesUtil;

public class BizLogicException extends Exception {

	private static final String EXPINFO = "<-[Biz Logic Excetion]->";
	private String msg;
	private int code = 0;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BizLogicException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public BizLogicException(int code) {
		super(PropertiesUtil.getProperties(String.valueOf(code)));
		this.code = code;
		this.msg = PropertiesUtil.getProperties(String.valueOf(code));
	}

	@Override
	public String getMessage() {
		return msg;
	}

	@Override
	public String toString() {
		return EXPINFO + super.toString();
	}
	
	public int getCode()
	{
		return code;
	}

}
