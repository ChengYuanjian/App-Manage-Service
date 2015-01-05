package com.cyj.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyj.bo.RetInfo;
import com.cyj.dao.DictionaryDao;
import com.cyj.util.LoggerUtil;

@Controller
@RequestMapping("/dict")
public class DictionaryController {

	private static LoggerUtil logger = new LoggerUtil(
			DictionaryController.class);
	@Autowired
	private DictionaryDao dictionaryDao;

	@RequestMapping(value = "/area", method = RequestMethod.GET)
	public @ResponseBody
	Object getArea() {
		try {
			Map<String, Object> result = (Map<String, Object>) dictionaryDao
					.getAllArea();
			if (MapUtils.isNotEmpty(result))
				return result;

			String path = this.getClass().getResource("/").getPath();
			StringBuffer sb = new StringBuffer();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(path + "json/areas.json"), "UTF-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			String js = sb.toString();
			if (!js.startsWith("{")) {
				js = js.substring(js.indexOf("{"));
			}
			return JSONObject.fromObject(js);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			RetInfo retInfo = new RetInfo(-30001);
			return RetInfo.toMap(retInfo);
		}
	}

	@RequestMapping(value = "/area/{code}", method = RequestMethod.GET)
	public @ResponseBody
	Object getAreaByCode(@PathVariable
	int code) {
		try {
			List<Map<String, Object>> result = dictionaryDao.getArea(code);
			if (CollectionUtils.isNotEmpty(result))
				return result;
			return new RetInfo(-30000);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			RetInfo retInfo = new RetInfo(-30001);
			return retInfo;
		}
	}

	@RequestMapping(value = "/pet", method = RequestMethod.GET)
	public @ResponseBody
	Object getPet() {
		try {
			Map<String, Object> result = (Map<String, Object>) dictionaryDao
					.getAllPet();
			if (MapUtils.isNotEmpty(result))
				return result;
			String path = this.getClass().getResource("/").getPath();
			StringBuffer sb = new StringBuffer();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(path + "json/pets.json"), "UTF-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			String js = sb.toString();
			if (!js.startsWith("{")) {
				js = js.substring(js.indexOf("{"));
			}
			return JSONObject.fromObject(js);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			RetInfo retInfo = new RetInfo(-30001);
			return RetInfo.toMap(retInfo);
		}
	}

	@RequestMapping(value = "/pet/{code}", method = RequestMethod.GET)
	public @ResponseBody
	Object getPetByCode(@PathVariable
	int code) {
		try {
			List<Map<String, Object>> result = dictionaryDao.getPet(code);
			if (CollectionUtils.isNotEmpty(result))
				return result;
			return new RetInfo(-30000);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			RetInfo retInfo = new RetInfo(-30001);
			return retInfo;
		}
	}
}
