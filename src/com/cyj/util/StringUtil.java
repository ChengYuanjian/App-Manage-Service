package com.cyj.util;

import org.apache.commons.lang.StringUtils;

public class StringUtil {

	public static boolean isContain(String srcStr, String subStr, String split) {
		if (StringUtils.isEmpty(srcStr))
			return false;
		if (null == subStr)
			return false;
		if (srcStr.equals(subStr))
			return true;
		else {
			if (srcStr.indexOf(split) < 0)
				return false;
			for (String s : srcStr.split(split)) {
				if (subStr.equals(s))
					return true;
			}
			return false;
		}
	}
	
	public static boolean isStartwith(String srcStr, String subStr, String split) {
		if (StringUtils.isEmpty(srcStr))
			return false;
		if (null == subStr)
			return false;
		if (srcStr.equals(subStr))
			return true;
		else {
			if (srcStr.indexOf(split) < 0)
				return false;
			for (String s : srcStr.split(split)) {
				if (subStr.startsWith(s))
					return true;
			}
			return false;
		}
	}

	public static String calc(String srcStr, String... strings) {
		if (StringUtils.isEmpty(srcStr))
			return null;
		if (strings == null)
			return null;

		for (int i = 0; i < strings.length; i++) {
			srcStr = srcStr.replace("{" + i + "}", strings[i]);
		}
		return srcStr;
	}

}
