package com.cyj.controller.test;

import org.junit.Test;

import com.cyj.base.test.HttpClientUtil;
import com.cyj.base.test.HttpClientUtil.METHOD;

public class UserControllerTest {
	
	@Test
	public void test_login()
	{
		String data = "{\"nickname\":\"萌小美2\",\"phone\":\"1111\",\"regtime\":\"2014-12-13\",\"iconurl\":\"/image/xx.png\",\"aaa\":\"bbb\"}";
		String result = HttpClientUtil.execute(METHOD.POST, HttpClientUtil.POSTURL+"user/login?from=website", data);
		System.out.println(result);
	}
	
	@Test
	public void test_getUser()
	{		
		String result = HttpClientUtil.execute(METHOD.GET, HttpClientUtil.POSTURL+"user/view/1?from=website", "");
		System.out.println(result);
	}

}
