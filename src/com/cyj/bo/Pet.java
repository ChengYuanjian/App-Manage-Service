package com.cyj.bo;

import java.io.Serializable;
import java.util.Date;

public class Pet implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8781498560984983295L;
	
	private long masterid;
	private long petid;
	private String petname;
	private Date birthday;//format: yyyy-MM-dd
	private int petsex;//1:male;0:female
	private String petcategory;
	private String pettype;
	public long getMasterid() {
		return masterid;
	}
	public void setMasterid(long masterid) {
		this.masterid = masterid;
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
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public int getPetsex() {
		return petsex;
	}
	public void setPetsex(int petsex) {
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
