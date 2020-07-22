package com.gee.utils;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.junit.Test;

import com.gee.ConfigTest;

import auto.service.HelloService;

public class RockMqUtilsTest extends ConfigTest{
	
	@Test
	public void getProducer() {
		System.out.println(ApplicationContextUtils.APP_CONTEXT.getBean(DefaultMQProducer.class).getNamesrvAddr());
		System.out.println(ApplicationContextUtils.APP_CONTEXT.getBean(DefaultMQPushConsumer.class).getNamesrvAddr());
		URL url = Thread.currentThread().getContextClassLoader().getResource("META-INF/spring.factories");
		System.out.println(url.getPath());
		System.out.println(ApplicationContextUtils.APP_CONTEXT.getBean(HelloService.class).sayHello());

	}
	
	@Test
	public void sendMsg() throws UnsupportedEncodingException {
		Message message = new Message();
		message.setTopic("test_topic");
		message.setTags("test_tag");
		String body = "this is a body";
		message.setBody(body.getBytes(RemotingHelper.DEFAULT_CHARSET));
		RockMqUtils.sendMsg(message);
	}
	
	@Test
	public void futureTask() throws InterruptedException, ExecutionException {
		Callable call = new MyCallable();
		FutureTask ft = new FutureTask<String>(call);
		Thread t = new Thread(ft);
		t.start();
		System.out.println(ft.get());
		
		MyThread mt = new MyThread();
		new Thread(mt).start();
		new Thread(mt).start();
		new Thread(mt).start();
	}
	
	
	
	class MyCallable implements Callable<String>{

		@Override
		public String call() throws Exception {
			// TODO Auto-generated method stub
			return "success";
		}
		
	}
	
	class MyThread extends Thread{
		@Override
		public void run() {
			 
		}
	}
}
