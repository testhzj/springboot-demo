package com.gee.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gee.entity.User;
import com.gee.mapper.UserMapper;

@Component
public class UserDAO {
	
	@Autowired
	private UserMapper userMapper;
	
	public List<User> getAllUser() {
		return userMapper.getAllUser();
	}
}
