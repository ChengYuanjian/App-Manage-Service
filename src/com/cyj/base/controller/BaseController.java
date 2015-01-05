package com.cyj.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyj.bo.RetInfo;
import com.cyj.util.PropertiesUtil;

@Controller
public class BaseController {

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/err/{id}", method = RequestMethod.GET)
	public @ResponseBody
	RetInfo error(@PathVariable("id")
	int id) {
		return new RetInfo(id, PropertiesUtil.getProperties(String.valueOf(id)));
	}
	
	
	@RequestMapping(value = "/api/test", method = RequestMethod.GET)
	public String apitest() {
		return "../pages/apitest";
	}

}
