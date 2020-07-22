package com.gee.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtils implements ApplicationContextAware{
	private ApplicationContext context;
	public static ApplicationContext APP_CONTEXT;
	public <T> T getBean(Class<T> t) {
		return context.getBean(t);
	}
	
	public String[] getBeanNames() {
		return context.getBeanDefinitionNames();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
		APP_CONTEXT = applicationContext;
	}

	public ApplicationContext getContext() {
		return context;
	}

	public void setContext(ApplicationContext context) {
		this.context = context;
	}
}
