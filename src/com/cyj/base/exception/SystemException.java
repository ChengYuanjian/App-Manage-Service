package com.cyj.base.exception;

public class SystemException extends Exception {
	private static final String EXPINFO = "<-[System Excetion]->";
	private String msg;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SystemException(String msg, Throwable t) {
		super(msg, t);
		this.msg = msg;
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

}
