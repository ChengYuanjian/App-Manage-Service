package com.cyj.bo;

import java.io.Serializable;

public class Token implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String signature;
	private long timestamp;
	private int nonce;
	private String echostr;
	
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public int getNonce() {
		return nonce;
	}
	public void setNonce(int nonce) {
		this.nonce = nonce;
	}
	public String getEchostr() {
		return echostr;
	}
	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}

}
