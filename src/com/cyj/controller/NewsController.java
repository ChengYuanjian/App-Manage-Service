package com.cyj.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyj.base.exception.BizLogicException;
import com.cyj.bo.RetInfo;
import com.cyj.util.LoggerUtil;

@Controller
@RequestMapping("/news")
public class NewsController {

	private static LoggerUtil logger = new LoggerUtil(NewsController.class);

	@RequestMapping(value = "/top/{from}/{to}", method = RequestMethod.GET)
	public @ResponseBody
	List<Map<String, Object>> getTopNews(@PathVariable
	String from, @PathVariable
	String to) {
		try {
			int frompage = Integer.parseInt(from);
			int topage = Integer.parseInt(to);

			if (topage < frompage)
				throw new BizLogicException(-40001);

			List<Map<String, Object>> topList = new ArrayList<Map<String, Object>>();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userid", 1);
			map.put("nickname", "萌小美");
			map.put("iconurl", "xx.png");
			map.put("petname", "小乖");
			map.put("pubtime", "2014-12-12 12:00:33");
			map.put("imgurl", "yy.png");
			map.put("msgcontent", "这是一只可爱的汪星人");
			map.put("agreenum", "123");
			map.put("commentnum", "111");
			map.put("agreeuser", new String[] { "2", "3" });
			map.put("commentid", new String[] { "23", "34" });
			topList.add(map);
			if (topList.size() < 1)
				throw new BizLogicException(-30000);
			return topList;
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
			RetInfo retInfo = new RetInfo(-40000);
			return RetInfo.toList(retInfo);
		} catch (BizLogicException e) {
			logger.error(e.getMessage(), e);
			RetInfo retInfo = new RetInfo(e.getCode());
			return RetInfo.toList(retInfo);
		}
	}

}
