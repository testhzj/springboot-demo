package com.gee.mapper;

import java.util.List;

import com.gee.entity.User;

public interface UserMapper {
	List<User> getAllUser();
	List<User> selectUser(String username, Integer sex);
	List<User> getUserOrder(int userId);
}
