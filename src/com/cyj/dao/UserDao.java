package com.cyj.dao;

import java.util.List;

import com.cyj.bo.RetInfo;
import com.cyj.bo.User;
import com.cyj.bo.UserDetail;

public interface UserDao {

	public User getUserbyPhone(long phone);

	public List<User> getUsersbyName(String nickname, int from, int to);

	public boolean addUser(User user);

	public boolean updateUser(User user);

	public RetInfo login(User user);
	
	public boolean addUserDetail(UserDetail detail);
	
	public boolean deleteUserDetail(long phone);
	
	public boolean updateUserDetail(UserDetail detail);
	
	public UserDetail getUserDetail(long phone);

}
