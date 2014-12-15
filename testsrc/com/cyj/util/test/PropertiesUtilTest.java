package com.cyj.util.test;

import junit.framework.Assert;

import org.junit.Test;

import com.cyj.util.PropertiesUtil;

public class PropertiesUtilTest {

	@Test
	public void test_getProperties() {
		String value = PropertiesUtil.getProperties("acl.device");
		Assert.assertEquals("android,ios,website,minisite", value);
	}

}
