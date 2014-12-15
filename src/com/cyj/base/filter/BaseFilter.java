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
		if (StringUtils.isEmpty(accesstoken)) {
			response.sendRedirect(request.getContextPath() + "/err/-1");
			return;
		}

		try {
			String decodeToken = SecurityUtil.decode(accesstoken);
			String[] decodeResults = decodeToken.split("|");
			String appid = decodeResults[0];
			String secret = decodeResults[1];
			String signature = decodeResults[2];

			long timelimit = Long.parseLong(PropertiesUtil
					.getProperties("token.time.limit"));

			if (System.currentTimeMillis() - Long.parseLong(decodeResults[3]) > timelimit) {
				response.sendRedirect(request.getContextPath() + "/err/-2");
				return;
			}

		} catch (SystemException e) {
			logger.error(e.getMessage(), e);
			response.sendRedirect(request.getContextPath() + "/err/-10002");
			return;
		}

		{
			logger.info("The token is " + accesstoken);
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
