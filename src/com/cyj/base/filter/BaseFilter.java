package com.cyj.base.filter;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import com.cyj.base.exception.SystemException;
import com.cyj.base.wrapper.MultiReadHttpServletRequestWrapper;
import com.cyj.bo.AccessLog;
import com.cyj.constants.CommonConstants;
import com.cyj.dao.AccessLogDao;
import com.cyj.dao.impl.AccessLogDaoImpl;
import com.cyj.util.LoggerUtil;
import com.cyj.util.PropertiesUtil;

//import org.apache.commons.lang.StringUtils;
//import com.cyj.base.exception.SystemException;
//import com.cyj.constants.CommonConstants;
//import com.cyj.util.PropertiesUtil;
//import com.cyj.util.SecurityUtil;
//import com.cyj.util.StringUtil;

public class BaseFilter implements Filter {
	private LoggerUtil logger = new LoggerUtil(BaseFilter.class);
	private AccessLogDao accessLogDao;

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		MultiReadHttpServletRequestWrapper multiReadHttpServletRequestWrapper = new MultiReadHttpServletRequestWrapper(
				request);

		String requestURI = request.getRequestURI();
		logger.info("The URI [" + requestURI + "] entered BaseFilter");
		AccessLog accessLog = new AccessLog();
		// default info
		accessLog.setOs(request
				.getHeader(CommonConstants.HTTP_HEADER_USER_AGENT));
		accessLog.setDevicename(request
				.getHeader(CommonConstants.HTTP_HEADER_HOST));
		accessLog.setAppversion(request.getHeader(CommonConstants.HTTP_HEADER_CONTENT_TYPE));
		accessLog.setDeviceid(request.getRemoteHost() + ":"
				+ request.getRemotePort());
		accessLog.setPhone(0);
		accessLog.setRequesturi(requestURI);

		String from = request.getParameter("from");// request source

		try {
			if (StringUtils.isEmpty(from)) {// app call
				accessLog.setAccessresource("APP");
				if ("GET".equalsIgnoreCase(request.getMethod())) {
					try {
						String device = request
								.getParameter(CommonConstants.REQ_DEVICE);
						String deviceid = request
								.getParameter(CommonConstants.REQ_DEVICE_ID);
						String system = request
								.getParameter(CommonConstants.REQ_SYSTEM);
						String appversion = request
								.getParameter(CommonConstants.APP_VERSION);
						String longitude = request
								.getParameter(CommonConstants.REQ_LONGITUDE);
						String latitude = request
								.getParameter(CommonConstants.REQ_LATITUDE);
						String phone = request
								.getParameter(CommonConstants.REQ_USER_ID);

						if (StringUtils.isEmpty(device)
								|| StringUtils.isEmpty(deviceid)
								|| StringUtils.isEmpty(phone))
							throw new SystemException(-10006);

						accessLog.setDevicename(device);
						accessLog.setDeviceid(deviceid);
						accessLog.setOs(system);
						accessLog.setAppversion(appversion);
						accessLog.setLongitude(longitude);
						accessLog.setLatitude(latitude);
						accessLog.setPhone(Long.parseLong(phone));

					} catch (Exception e) {
						logger.error(-10006, e);
						throw new SystemException(-10006);
					}
				} else {
					try {
						String jsonStr = readJSONString(multiReadHttpServletRequestWrapper);
						if (StringUtils.isEmpty(jsonStr)) {
							throw new SystemException(-10005);
						}

						if (!jsonStr.startsWith("{"))
							jsonStr = jsonStr.substring(jsonStr.indexOf("{"));

						JSONObject jsonObject = JSONObject.fromObject(jsonStr);

						String device = jsonObject
								.getString(CommonConstants.REQ_DEVICE);
						String deviceid = jsonObject
								.getString(CommonConstants.REQ_DEVICE_ID);
						String system = jsonObject
								.getString(CommonConstants.REQ_SYSTEM);
						String appversion = jsonObject
								.getString(CommonConstants.APP_VERSION);
						String longitude = jsonObject
								.getString(CommonConstants.REQ_LONGITUDE);
						String latitude = jsonObject
								.getString(CommonConstants.REQ_LATITUDE);

						if (StringUtils.isEmpty(device)
								|| StringUtils.isEmpty(deviceid)
								|| StringUtils
										.isEmpty(jsonObject
												.getString(CommonConstants.REQ_USER_ID)))
							throw new SystemException(-10006);
						long userid = jsonObject
								.getLong(CommonConstants.REQ_USER_ID);

						accessLog.setDevicename(device);
						accessLog.setDeviceid(deviceid);
						accessLog.setOs(system);
						accessLog.setAppversion(appversion);
						accessLog.setLongitude(longitude);
						accessLog.setLatitude(latitude);
						accessLog.setPhone(userid);
					} catch (Exception e) {
						logger.error(-10004, e);
						throw new SystemException(-10004);
					}
				}

			} else {// others call
				accessLog.setAccessresource(from);
				if (PropertiesUtil.getProperties("acl.device").indexOf(from) == -1) {
					throw new SystemException(-10007);
				}
			}

		} catch (SystemException e) {
			response.sendRedirect(request.getContextPath() + "/err/"
					+ e.getCode());
			return;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.sendRedirect(request.getContextPath() + "/err/0");
			return;
		}finally{
			writeAccessLog(accessLog);
		}

		/***********************************************************************
		 * String accesstoken =
		 * request.getParameter(CommonConstants.ACCESSTOKEN);
		 * 
		 * logger.info("The token is " + accesstoken);
		 * 
		 * if (StringUtils.isEmpty(accesstoken)) {//empty token
		 * response.sendRedirect(request.getContextPath() + "/err/-1"); return; }
		 * 
		 * try { String decodeToken = SecurityUtil.decode(accesstoken); String[]
		 * decodeResults = decodeToken.split("\\|");
		 * 
		 * if (decodeResults.length != 4) {//illegal token
		 * response.sendRedirect(request.getContextPath() + "/err/-1"); return; }
		 * 
		 * String appid = decodeResults[0]; String secret = decodeResults[1];
		 * String signature = decodeResults[2]; // developer mode if
		 * (PropertiesUtil.getProperties("developer.signature")
		 * .equalsIgnoreCase(signature) && StringUtil.isContain(PropertiesUtil
		 * .getProperties("developer.appid"), appid, ",") &&
		 * StringUtil.isContain(PropertiesUtil
		 * .getProperties("developer.secret"), secret, ",")) {
		 * logger.info("----------Enter Developer Mode-----------"); } else { if
		 * (StringUtils.isNumeric(decodeResults[3])) { long timestamp =
		 * Long.parseLong(decodeResults[3]);
		 * 
		 * long timelimit = Long.parseLong(PropertiesUtil
		 * .getProperties("token.time.limit"));
		 * 
		 * if (System.currentTimeMillis() - timestamp > timelimit) {//token is
		 * expired response.sendRedirect(request.getContextPath() + "/err/-2");
		 * return; } } else{//invalid timestamp
		 * response.sendRedirect(request.getContextPath() + "/err/-1");
		 * logger.info("Invalid timestamp"); return; } } } catch
		 * (SystemException e) { logger.error(e.getMessage(), e);
		 * response.sendRedirect(request.getContextPath() + "/err/-10002");
		 * return; }
		 **********************************************************************/
		chain.doFilter(multiReadHttpServletRequestWrapper, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		ApplicationContext context = (ApplicationContext) config
				.getServletContext()
				.getAttribute(
						WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);

		accessLogDao = (AccessLogDaoImpl) context.getBean("accessLogDao");

		// Jedis jedis = RedisUtil.getInstance().getJedis();
	}
	
	private void writeAccessLog(AccessLog alog){
		accessLogDao.addAccessLog(alog);
	}

	@Override
	public void destroy() {

	}

	private String readJSONString(MultiReadHttpServletRequestWrapper request) {
		StringBuffer json = new StringBuffer();
		String line = null;
		BufferedReader reader = null;
		try {
			// reader = new BufferedReader(new
			// InputStreamReader(request.getInputStream(),"UTF-8"));
			reader = request.getReader();
			while ((line = reader.readLine()) != null) {
				json.append(line);
			}
		} catch (Exception e) {
			return "";
		} finally {
			if (null != reader)
				try {
					reader.close();
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
		}
		return json.toString();
	}
}
