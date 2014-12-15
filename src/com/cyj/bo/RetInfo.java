package com.cyj.bo;

import java.io.Serializable;

import com.cyj.util.PropertiesUtil;

public class RetInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3538348724651560461L;

	private int retcode;
	private String retmsg;

	public RetInfo(int retcode, String retmsg) {
		this.retcode = retcode;
		this.retmsg = retmsg;
	}

	public RetInfo(int retcode) {
		this.retcode = retcode;
		this.retmsg = PropertiesUtil.getProperties(String.valueOf(retcode));
	}

	public int getRetcode() {
		return retcode;
	}

	public void setRetcode(int retcode) {
		this.retcode = retcode;
	}

	public String getRetmsg() {
		return retmsg;
	}

	public void setRetmsg(String retmsg) {
		this.retmsg = retmsg;
	}

}
