package com.cyj.util.test;

import junit.framework.Assert;

import org.junit.Test;

import com.cyj.base.exception.SystemException;
import com.cyj.util.SecurityUtil;

public class SecurityUtilTest {

	@Test
	public void test_md5() throws SystemException {
		String md5 = SecurityUtil.md5("CYJ");
		System.out.println(md5);
		Assert.assertEquals("==wZ0Qj+sjtCj55AOQlg+mok", md5);
	}

	@Test
	public void test_md51() throws SystemException {
		String accesstoken = "MM|CYJ|DEV|0";
		String encodetoken = SecurityUtil.encode(accesstoken);//CYJ:fF6JMWH0R9YVGm7F9vG0GA==;DQ:NUd+NwnV1ZYVJqxDLQrSVg==
		System.out.println(accesstoken + ":" + encodetoken);

		Assert.assertEquals(accesstoken, SecurityUtil.decode(encodetoken));

		String token = "APP_TOKEN";
		String entoken =SecurityUtil.encode(token);
		System.out.println("Base64 with a key: Token is "+token+",and encode is "+entoken+",and decode is "+SecurityUtil.decode(entoken));
	}
	
	@Test
	public void test_base64() throws SystemException
	{
		String token = "APP_TOKEN";
		String entoken =SecurityUtil.base64encode(token);
		System.out.println("Base64:Token is "+token+",and encode is "+entoken+",and decode is "+SecurityUtil.base64decode(entoken));
	}

}
