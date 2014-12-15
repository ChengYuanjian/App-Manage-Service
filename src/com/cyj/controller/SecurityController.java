package com.cyj.controller;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cyj.base.exception.SystemException;
import com.cyj.bo.RetInfo;
import com.cyj.bo.SecurityInfo;
import com.cyj.util.LoggerUtil;
import com.cyj.util.PropertiesUtil;
import com.cyj.util.SecurityUtil;

@Controller
@RequestMapping("/security")
public class SecurityController {

	private LoggerUtil logger = new LoggerUtil(SecurityController.class);

	@RequestMapping(value = "/token", method = RequestMethod.GET)
	public String getToken(SecurityInfo securityInfo) {
		String appid = securityInfo.getAppid();
		if (securityInfo == null || StringUtils.isEmpty(appid))
			return JSONObject.fromObject(new RetInfo(-20004)).toString();
		else {
			String secretid = securityInfo.getSecretid();
			String signature = securityInfo.getSignature();

			if (PropertiesUtil.getProperties("acl.device").indexOf(signature) < 0)
				return JSONObject.fromObject(new RetInfo(-20005)).toString();
			try {
				securityInfo.setAccesstoken(generateToken(appid, secretid,
						signature));
			} catch (SystemException e) {
				logger.error(e.getMessage(), e);
				return JSONObject.fromObject(new RetInfo(0, e.getMessage()))
						.toString();
			}
			return JSONObject.fromObject(securityInfo).toString();
		}
	}

	private String generateToken(String appid, String secretid, String signature)
			throws SystemException {
		String token = SecurityUtil.getAccessToken(appid, secretid, signature);
		return token;
	}
}
