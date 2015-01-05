package com.cyj.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyj.base.exception.BizLogicException;
import com.cyj.bo.RetInfo;
import com.cyj.bo.User;
import com.cyj.bo.UserDetail;
import com.cyj.dao.UserDao;
import com.cyj.util.JSONUtil;
import com.cyj.util.LoggerUtil;

@Controller
@RequestMapping("/user")
public class UserController {

	private LoggerUtil logger = new LoggerUtil(UserController.class);

	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody
	RetInfo login(@RequestBody
	Map<String, Object> map) {
		User user = (User) JSONUtil.map2bean(map, User.class);
		return userDao.login(user);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public @ResponseBody
	RetInfo updateUser(@RequestBody
	Map<String, Object> map) {
		User user = (User) JSONUtil.map2bean(map, User.class);
		if (userDao.updateUser(user))
			return new RetInfo(1);
		return new RetInfo(0);
	}

	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Object getUser(@PathVariable("id")
	long id) {
		User user = getUserDao().getUserbyPhone(id);
		if (user == null)
			return new RetInfo(-30000);
		return user;
	}

	@RequestMapping(value = "/list/{from}/{to}", method = RequestMethod.GET)
	public @ResponseBody
	Object getUsers(
			@RequestParam(value = "name", required = false, defaultValue = "")
			String name, @PathVariable
			String from, @PathVariable
			String to) {
		try {
			int frompage = Integer.parseInt(from);
			int topage = Integer.parseInt(to);

			if (topage < frompage)
				throw new BizLogicException(-40001);

			List<User> userlist = getUserDao().getUsersbyName(name, frompage,
					topage);
			if (userlist == null || userlist.size() < 1)
				throw new BizLogicException(-30000);
			return userlist;
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
			return new RetInfo(-40000);
		} catch (BizLogicException e) {
			logger.error(e.getMessage(), e);
			return new RetInfo(e.getCode());
		}
	}

	@RequestMapping(value = "/detail/c", method = RequestMethod.POST)
	public @ResponseBody
	RetInfo addUserDetail(@RequestBody
	Map<String, Object> map) {
		try {
			UserDetail userdetail = (UserDetail) JSONUtil.map2bean(map,
					UserDetail.class);
			userDao.addUserDetail(userdetail);
			return new RetInfo(1);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new RetInfo(0);
		}
	}

	@RequestMapping(value = "/detail/u", method = RequestMethod.PUT)
	public @ResponseBody
	RetInfo updateUserDetail(@RequestBody
	Map<String, Object> map) {
		try {
			UserDetail userdetail = (UserDetail) JSONUtil.map2bean(map,
					UserDetail.class);
			userDao.updateUserDetail(userdetail);
			return new RetInfo(1);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new RetInfo(0);
		}
	}

	@RequestMapping(value = "/detail/view/{phone}", method = RequestMethod.GET)
	public @ResponseBody
	Object queryUserDetail(@PathVariable
	long phone) {
		UserDetail userdetail = userDao.getUserDetail(phone);
		if (userdetail == null)
			return new RetInfo(-30000);
		return userdetail;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
