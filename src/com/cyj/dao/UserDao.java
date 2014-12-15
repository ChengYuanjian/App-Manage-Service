package com.cyj.dao;

import com.cyj.bo.RetInfo;
import com.cyj.bo.User;

public interface UserDao {

	public User getUserbyId(long userId);

	public RetInfo addUser(User user);

	public RetInfo updateUser(User user);
	
	public RetInfo login(User user);

}
