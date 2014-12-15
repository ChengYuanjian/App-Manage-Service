package com.cyj.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyj.bo.RetInfo;
import com.cyj.bo.User;
import com.cyj.dao.UserDao;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody
	RetInfo login(User user) {
		return userDao.login(user);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody
	RetInfo addUser(User user) {
		return userDao.addUser(user);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public @ResponseBody
	RetInfo updateUser(User user) {
		return userDao.updateUser(user);
	}

	@RequestMapping(value = "/query/{id}", method = RequestMethod.GET)
	public @ResponseBody
	User getUser(@PathVariable("id")
	long id) {
		return getUserDao().getUserbyId(id);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
	List<User> getUsers(
			@RequestParam(value = "name", required = false, defaultValue = "")
			String name) {
		User user = new User();
		user.setNickname("测试用户1");
		user.setSex(1);
		user.setBirthday(new Date());
		user.setConstellation(2);

		User user2 = new User();
		user2.setNickname("测试用户2");
		user2.setSex(2);
		user2.setBirthday(new Date());
		user2.setConstellation(12);

		List<User> list = new ArrayList<User>();
		list.add(user);
		list.add(user2);

		return list;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
