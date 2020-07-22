package com.gee.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gee.ConfigTest;
import com.gee.service.UserService;

public class UserServcieImplTest extends ConfigTest{
	
	@Autowired
	private UserService userService;
	
	@Test
	public void getAllUser() {
		System.out.println(userService.getAllUser());
	}
}
