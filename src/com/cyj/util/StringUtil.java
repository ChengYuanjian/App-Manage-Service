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
}
