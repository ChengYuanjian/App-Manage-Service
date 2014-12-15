package com.cyj.dao.impl;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.cyj.bo.RetInfo;
import com.cyj.bo.User;
import com.cyj.dao.UserDao;

public class UserDaoImpl implements UserDao {
	public User getUserbyId(long userId) {
		User user = new User();
		user.setUserid(userId);
		user.setNickname("测试用户");
		user.setSex(1);
		user.setBirthday(new Date());
		user.setConstellation(2);
		user.setCity("广州");
		user.setCountry("中国");
		user.setDiscrict("天河区");
		user.setEmail("test@test.com");
		user.setPhone("1234567890");
		user.setProvince("广东");
		user.setWechat("weixintest");
		user.setWeibo("weibotest");
		return user;
	}

	public RetInfo addUser(User user) {
		return new RetInfo(1);
	}

	public RetInfo updateUser(User user) {
		return new RetInfo(1);
	}

	@Override
	public RetInfo login(User user) {
		if (user != null && StringUtils.isNotEmpty(user.getEmail())
				&& !StringUtils.isNotEmpty(user.getPwd()) && isVerify(user))
			return new RetInfo(1);
		return new RetInfo(-20001);
	}

	private boolean isVerify(User user) {
		return true;
	}
}
