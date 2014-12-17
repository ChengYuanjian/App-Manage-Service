package com.cyj.base.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.cyj.base.exception.SystemException;
import com.cyj.constants.CommonConstants;
import com.cyj.util.LoggerUtil;
import com.cyj.util.PropertiesUtil;
import com.cyj.util.SecurityUtil;
import com.cyj.util.StringUtil;

public class BaseFilter implements Filter {
	private LoggerUtil logger = new LoggerUtil(BaseFilter.class);

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		logger.info("The URI [" + request.getRequestURI()
				+ "] entered BaseFilter");
		String accesstoken = request.getParameter(CommonConstants.ACCESSTOKEN);
		
		logger.info("The token is " + accesstoken);
		
		if (StringUtils.isEmpty(accesstoken)) {//empty token
			response.sendRedirect(request.getContextPath() + "/err/-1");
			return;
		}

		try {
			String decodeToken = SecurityUtil.decode(accesstoken);
			String[] decodeResults = decodeToken.split("\\|");

			if (decodeResults.length != 4) {//illegal token
				response.sendRedirect(request.getContextPath() + "/err/-1");
				return;
			}

			String appid = decodeResults[0];
			String secret = decodeResults[1];
			String signature = decodeResults[2];

			// developer mode
			if (PropertiesUtil.getProperties("developer.signature")
					.equalsIgnoreCase(signature)
					&& StringUtil.isContain(PropertiesUtil
							.getProperties("developer.appid"), appid, ",")
					&& StringUtil.isContain(PropertiesUtil
							.getProperties("developer.secret"), secret, ",")) {
				logger.info("----------Enter Developer Mode-----------");
			} else {
				if (StringUtils.isNumeric(decodeResults[3])) {
					long timestamp = Long.parseLong(decodeResults[3]);

					long timelimit = Long.parseLong(PropertiesUtil
							.getProperties("token.time.limit"));

					if (System.currentTimeMillis() - timestamp > timelimit) {//token is expired
						response.sendRedirect(request.getContextPath()
								+ "/err/-2");
						return;
					}
				}
				else{//invalid timestamp
					response.sendRedirect(request.getContextPath()
							+ "/err/-1");
					logger.info("Invalid timestamp");
					return;
				}
			}

		} catch (SystemException e) {
			logger.error(e.getMessage(), e);
			response.sendRedirect(request.getContextPath() + "/err/-10002");
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// Jedis jedis = RedisUtil.getInstance().getJedis();
	}

	@Override
	public void destroy() {

	}

}
