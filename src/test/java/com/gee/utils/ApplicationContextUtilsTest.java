package com.gee.utils;

import java.net.URL;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.junit.Test;

import com.gee.ConfigTest;

import auto.service.HelloService;

public class ApplicationContextUtilsTest extends ConfigTest{
	@Test
	public void getBean() {
		System.out.println(ApplicationContextUtils.APP_CONTEXT.getBean(HelloService.class).sayHello());

	}
}
