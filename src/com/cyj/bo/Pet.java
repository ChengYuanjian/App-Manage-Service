package com.cyj.bo;

import java.io.Serializable;

public class Pet implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8781498560984983295L;
	
	private long phone;
	private long petid;
	private String petname;
	private String birthday;//format: yyyy-MM-dd
	private String petsex;
	private String petcategory;
	private String pettype;
	
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public long getPetid() {
		return petid;
	}
	public void setPetid(long petid) {
		this.petid = petid;
	}
	public String getPetname() {
		return petname;
	}
	public void setPetname(String petname) {
		this.petname = petname;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPetsex() {
		return petsex;
	}
	public void setPetsex(String petsex) {
		this.petsex = petsex;
	}
	public String getPetcategory() {
		return petcategory;
	}
	public void setPetcategory(String petcategory) {
		this.petcategory = petcategory;
	}
	public String getPettype() {
		return pettype;
	}
	public void setPettype(String pettype) {
		this.pettype = pettype;
	}
	

}
