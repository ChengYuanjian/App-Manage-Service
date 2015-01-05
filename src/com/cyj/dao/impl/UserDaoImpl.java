package com.cyj.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.cyj.base.dao.BaseDAO;
import com.cyj.bo.RetInfo;
import com.cyj.bo.User;
import com.cyj.bo.UserDetail;
import com.cyj.dao.UserDao;
import com.cyj.util.LoggerUtil;

public class UserDaoImpl extends BaseDAO implements UserDao {

	private String namespace = "com.cyj.dao.UserDao";

	private LoggerUtil logger = new LoggerUtil(UserDaoImpl.class);

	public User getUserbyPhone(long phone) {
		try {
			return (User) queryForObject(namespace + ".getUserByPhone", phone);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public boolean addUser(User user) {
		try {
			add(namespace + ".addUser", user);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	public boolean updateUser(User user) {
		try {
			update(namespace + ".updateUserByPhone", user);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	@Override
	public RetInfo login(User user) {
		try {
			if (user != null
					&& StringUtils.isNotEmpty(String.valueOf(user.getPhone()))
					&& isVerify(user)) {

				User resultUser = (User) queryForObject(namespace
						+ ".getUserByPhone", user.getPhone());
				if (resultUser == null) {// user does not exist
					add(namespace + ".addUser", user);
				}
				return new RetInfo(1);
			}
			return new RetInfo(-20002);
		} catch (Exception e) {
			logger.error(-1, e);
			return new RetInfo(-20002);
		}
	}

	private boolean isVerify(User user) {
		return true;
	}

	@Override
	public List<User> getUsersbyName(String nickname, int from, int to) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("nickname", nickname);
			map.put("from", from);
			map.put("to", to);
			return (List<User>) queryForList(namespace + ".getUserByName", map);
		} catch (Exception e) {
			logger.error(-1, e);
			return null;
		}
	}

	@Override
	public boolean addUserDetail(UserDetail detail) {
		try {
			add(namespace + ".addUserDetail", detail);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	@Override
	public boolean deleteUserDetail(long phone) {
		try {
			delete(namespace + ".deleteUserDetail", phone);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	@Override
	public boolean updateUserDetail(UserDetail detail) {
		try {
			update(namespace + ".updateUserDetail", detail);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	@Override
	public UserDetail getUserDetail(long phone) {
		try {
			return (UserDetail) queryForObject(namespace
					+ ".getUserDetailByPhone", phone);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

}
