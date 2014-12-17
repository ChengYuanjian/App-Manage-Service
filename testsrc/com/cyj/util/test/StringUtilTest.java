package com.cyj.util.test;

import org.junit.Test;

import com.cyj.util.StringUtil;

import junit.framework.Assert;

public class StringUtilTest {
	
	@Test
	public void test_isContain()
	{
		Assert.assertEquals(true,StringUtil.isContain("a,b,c", "c", ","));
		Assert.assertEquals(false,StringUtil.isContain("a,.b,.c", "c", ","));
	}

}
