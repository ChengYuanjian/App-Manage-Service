package com.cyj.controller;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyj.base.exception.SystemException;
import com.cyj.bo.RetInfo;
import com.cyj.bo.SecurityInfo;
import com.cyj.bo.Token;
import com.cyj.util.LoggerUtil;
import com.cyj.util.PropertiesUtil;
import com.cyj.util.SecurityUtil;

@Controller
@RequestMapping("/auth")
public class SecurityController {

	private LoggerUtil logger = new LoggerUtil(SecurityController.class);

	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public @ResponseBody
	RetInfo authToken(Token token) {
		RetInfo retInfo = null;
		
		if(token == null)
			retInfo = new RetInfo(-1);
		
		try {
			String signature = token.getSignature();
			String echostr = token.getEchostr();
			long timestamp = token.getTimestamp();
			int nonce = token.getNonce();
			
			if(StringUtils.isEmpty(signature)||StringUtils.isEmpty(echostr))
				retInfo = new RetInfo(-1);
			
			long timelimit = Long.parseLong(PropertiesUtil
					.getProperties("token.time.limit"));
			if (System.currentTimeMillis() - timestamp > timelimit) {//token is expired
				retInfo = new RetInfo(-2);
			}
			
			if(SecurityUtil.validate(signature, String.valueOf(timestamp),String.valueOf(nonce)))
				retInfo = new RetInfo(0,echostr);
			else
				retInfo = new RetInfo(-1);			
		} catch (RuntimeException e) {
			logger.error(e.getMessage(), e);
			retInfo = new RetInfo(-1);
		}
		catch (SystemException e) {
			logger.error(e.getMessage(), e);
			retInfo = new RetInfo(-1);
		}	
		return retInfo;
	}

	@RequestMapping(value = "/token1", method = RequestMethod.POST)
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
