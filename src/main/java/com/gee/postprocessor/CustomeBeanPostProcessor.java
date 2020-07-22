package com.gee.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.gee.utils.ApplicationContextUtils;

//@Component
public class CustomeBeanPostProcessor implements BeanPostProcessor{
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if(bean instanceof ApplicationContextUtils) {
			ApplicationContextUtils appContextUtilsInstance = (ApplicationContextUtils)bean;
			appContextUtilsInstance.APP_CONTEXT = appContextUtilsInstance.getContext();
		}
		return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
	}
}
