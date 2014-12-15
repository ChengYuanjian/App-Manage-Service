package com.cyj.base.exception;

public class BizLogicException extends Exception {

	private static final String EXPINFO = "<-[Biz Logic Excetion]->";
	private String msg;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BizLogicException(String msg) {
		super(msg);
		this.msg = msg;
	}

	@Override
	public String getMessage() {
		return msg;
	}

	@Override
	public String toString() {
		return EXPINFO + super.toString();
	}

}
