package com.cyj.base.test;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

public class HttpClientUtil {
	
	@Test
	public void test()
	{
		CloseableHttpClient client = HttpClients.createDefault();
	}

}
