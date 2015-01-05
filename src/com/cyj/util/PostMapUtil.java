package com.cyj.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

import com.cyj.constants.CommonConstants;

public class PostMapUtil {
	
	public static Map<String,Object> splitPostMap(Map<String,Object> postMap)
	{
		Map<String,Object> result = null;
		if(MapUtils.isNotEmpty(postMap)){
			result = new HashMap<String,Object>();
			result.put(CommonConstants.REQ_DEVICE, postMap.get(CommonConstants.REQ_DEVICE));
			result.put(CommonConstants.REQ_DEVICE_ID, postMap.get(CommonConstants.REQ_DEVICE_ID));
		}	
		return result;
	}

}
