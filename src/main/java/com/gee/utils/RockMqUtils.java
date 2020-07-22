package com.gee.utils;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
public class RockMqUtils {
	public static DefaultMQProducer getDefaultMQProducer() {
		return ApplicationContextUtils.APP_CONTEXT.getBean(DefaultMQProducer.class);
	}
	
	public static DefaultMQPushConsumer getDefaultMQPushConsumer() {
		return ApplicationContextUtils.APP_CONTEXT.getBean(DefaultMQPushConsumer.class);
	}
	
	
	public static void sendMsg(Message msg) {
		try {
			getDefaultMQProducer().send(msg);
		} catch (MQClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemotingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MQBrokerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
