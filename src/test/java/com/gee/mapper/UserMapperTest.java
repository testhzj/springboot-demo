package com.gee.mapper;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gee.ConfigTest;

public class UserMapperTest extends ConfigTest{
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private OrdersMapper orderMapper;
	@Test
	public void userMapper() {
		//userMapper.selectUser("小明", 1);
		//userMapper.getUserOrder(1);
		
		orderMapper.getOrderUser(3);
	}
}
