package com.cyj.bo;

import java.io.Serializable;

public class AccessLog implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long logid;
	private String devicename;
	private String deviceid;
	private String os;
	private String appversion;
	private String longitude;
	private String latitude;
	private long phone;
	private String accesstime;//yyyy-MM-dd hh:mm:ss
	private String requesturi;
	private String accessresource;
	
	public String getDevicename() {
		return devicename;
	}
	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getAppversion() {
		return appversion;
	}
	public void setAppversion(String appversion) {
		this.appversion = appversion;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getAccesstime() {
		return accesstime;
	}
	public void setAccesstime(String accesstime) {
		this.accesstime = accesstime;
	}
	public long getLogid() {
		return logid;
	}
	public void setLogid(long logid) {
		this.logid = logid;
	}
	public String getRequesturi() {
		return requesturi;
	}
	public void setRequesturi(String requesturi) {
		this.requesturi = requesturi;
	}
	public String getAccessresource() {
		return accessresource;
	}
	public void setAccessresource(String accessresource) {
		this.accessresource = accessresource;
	}
}
