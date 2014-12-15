package com.cyj.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cyj.util.LoggerUtil;

public class BaseInterceptor extends HandlerInterceptorAdapter {

	private LoggerUtil logger = new LoggerUtil(BaseInterceptor.class);
	private long startTimeMillis = 0;

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
		logger.info("The URI [" + request.getRequestURI()
				+ "] entered BaseInterceptor");
		startTimeMillis = System.currentTimeMillis();

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
		logger.info("The URI [" + request.getRequestURI()
				+ "] left BaseInterceptor");
		logger.info("The URI [" + request.getRequestURI() + "] cost "
				+ (System.currentTimeMillis() - startTimeMillis)
				+ "ms in total");
	}

}
