package com.gee.mq;

import java.util.List;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfiguration {
	
	@Value("${rocketmq.producer.group}")
	private String group;
	@Value("${rocketmq.nameServer}")
	private String nameServer;
	@Value("${rocketmq.consume.group}")
	private String consumeGroup;
//	@Bean通过spring来自动配置，所以不需要了
//	public DefaultMQProducer getDefaultMQProducer() {
//		DefaultMQProducer mqProducer = new DefaultMQProducer();
//		mqProducer.setProducerGroup(group);
//		mqProducer.setNamesrvAddr(nameServer);
//		return mqProducer;
//	}
	
	@Bean
	public DefaultMQPushConsumer getDefaultMQPushConsumer() throws MQClientException {
		DefaultMQPushConsumer mqushConsumer = new DefaultMQPushConsumer();
		mqushConsumer.setConsumerGroup(consumeGroup);
		mqushConsumer.setNamesrvAddr(nameServer);
		mqushConsumer.subscribe("test_topic", "*");
		mqushConsumer.registerMessageListener(new MessageListenerOrderly() {

			@Override
			public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
				System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                return ConsumeOrderlyStatus.SUCCESS;
			}
			
		});
		mqushConsumer.start();
		return mqushConsumer;
	}
}
