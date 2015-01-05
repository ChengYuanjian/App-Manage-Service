package com.cyj.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public static Map<String,Object> toMap(RetInfo retinfo)
	{
		if(retinfo == null)
			return null;
		
		Map<String,Object> retMap = new HashMap<String,Object>();
		retMap.put("retcode", retinfo.getRetcode());
		retMap.put("retmsg", retinfo.getRetmsg());
		return retMap;
	}
	
	public static List<Map<String,Object>> toList(RetInfo retinfo)
	{
		List<Map<String,Object>> retList = new ArrayList<Map<String,Object>>();
		retList.add(toMap(retinfo));
		return retList;
	}

}
