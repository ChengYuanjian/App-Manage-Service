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
	
	@Test
	public void test_isStartwith()
	{
		Assert.assertEquals(true,StringUtil.isStartwith("aa,bb,c", "c/b.html", ","));
		Assert.assertEquals(false,StringUtil.isStartwith("aa,.bb,.c", "c", ","));
	}
	
	@Test
	public void test_calc(){
		String srcStr = "This is {0}, and your name is {1},{},{2}()(){{3}}";
		String result = StringUtil.calc(srcStr, "A","b","","s");
		System.out.println(result);
	}

}
