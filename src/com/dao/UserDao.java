package com.dao;

import org.apache.ibatis.annotations.Param;

import com.po.User;

public interface UserDao {
	public User queryUserByUsername(String username);
	public User queryUserByUsernameAndPassword(@Param("username")String username, 
			@Param("password")String
			password);
	public int saveUser(User user);
}
