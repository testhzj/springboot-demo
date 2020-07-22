package com.gee.controller;

import java.io.UnsupportedEncodingException;

import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gee.utils.RockMqUtils;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@GetMapping(value = "/hello")
	public String test() {
		System.out.println("hello world");
		return "hello world";
	}
	
	@GetMapping("/sendMsg")
	public String sendMsg() throws UnsupportedEncodingException {
		Message message = new Message();
		message.setTopic("test_topic");
		message.setTags("test_tag");
		String body = "this is a body";
		message.setBody(body.getBytes(RemotingHelper.DEFAULT_CHARSET));
		RockMqUtils.sendMsg(message);
		return "sendsuccess";
	}
}
