package com.cyj.base.test;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {

	public static String POSTURL = "http://localhost:8080/ams/";

	public enum METHOD {
		POST, DELETE, PUT, GET
	}

	public static String execute(METHOD method, String url, String data) {
		HttpUriRequest request = null;
		switch (method) {
		case POST:
			request = new HttpPost(url);
			break;
		case DELETE:
			request = new HttpDelete(url);
			break;
		case PUT:
			request = new HttpPut(url);
			break;
		case GET:
			request = new HttpGet(url);
			break;
		}

		try {
			CloseableHttpClient client = HttpClients.createDefault();
			if (StringUtils.isNotEmpty(data)) {
				StringEntity stringEntity = new StringEntity(data,"UTF-8");
				stringEntity.setContentEncoding("UTF-8");
				stringEntity.setContentType("application/json");
				if (request instanceof HttpPost) {
					((HttpPost) request).setEntity(stringEntity);
				} else if (request instanceof HttpPut) {
					((HttpPut) request).setEntity(stringEntity);
				}
			}

			HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();
			StatusLine statusLine = response.getStatusLine();

			if (entity != null
					&& statusLine.getStatusCode() == HttpStatus.SC_OK) {
				String json = EntityUtils.toString(entity, "UTF-8");
				return json;
			} else
				return statusLine.toString();

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		} finally {
			if (request instanceof HttpPost) {
				((HttpPost) request).releaseConnection();
			} else if (request instanceof HttpPut) {
				((HttpPut) request).releaseConnection();
			} else if (request instanceof HttpDelete) {
				((HttpDelete) request).releaseConnection();
			} else if (request instanceof HttpGet) {
				((HttpGet) request).releaseConnection();
			}
		}
	}
}
