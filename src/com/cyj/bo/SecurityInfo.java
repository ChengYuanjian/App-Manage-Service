package com.cyj.bo;

import java.io.Serializable;

public class SecurityInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4174113558633966612L;
	private String appid = "";
	private String secretid = "";
	private String signature = "";
	private String accesstoken = "";

	public SecurityInfo(String appid, String secretid, String signature,
			String accesstoken) {
		this.appid = appid;
		this.secretid = secretid;
		this.signature = signature;
		this.accesstoken = accesstoken;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getSecretid() {
		return secretid;
	}

	public void setSecretid(String secretid) {
		this.secretid = secretid;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getAccesstoken() {
		return accesstoken;
	}

	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}

}
