package com.cyj.util.test;


import junit.framework.Assert;

import org.junit.Test;

import com.cyj.base.exception.SystemException;
import com.cyj.util.SecurityUtil;

public class SecurityUtilTest {
	
	@Test
	public void test_md5() throws SystemException
	{
		String md5 = SecurityUtil.md5("CYJ");
		System.out.println(md5);
		Assert.assertEquals("==wZ0Qj+sjtCj55AOQlg+mok", md5);
	}
	
	@Test
	public void test_md51() throws SystemException
	{
		String md5 = SecurityUtil.md5("1");
		System.out.println(md5);
		
		String encodeStr =SecurityUtil.encode("CYJ");
		System.out.println(encodeStr);
		
		System.out.println(SecurityUtil.decode(encodeStr));
		//Assert.assertEquals("==wZ0Qj+sjtCj55AOQlg+mok", md5);
	}

}
