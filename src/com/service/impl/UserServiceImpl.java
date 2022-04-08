package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDao;
import com.po.User;
import com.service.UserService;
@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao ud;
	@Override
	public User queryUserByUsername(String username) {
		// TODO Auto-generated method stub
		return this.ud.queryUserByUsername(username);
	}
	@Override
	public User queryUserByUsernameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		return this.ud.queryUserByUsernameAndPassword(username, password);
	}
	@Override
	public int saveUser(User user) {
		// TODO Auto-generated method stub
		return this.ud.saveUser(user);
	}

}
