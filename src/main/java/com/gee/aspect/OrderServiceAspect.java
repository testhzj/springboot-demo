package com.gee.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class OrderServiceAspect {
	
	@Pointcut("execution(* com.gee.service..*(..))")
	public void pointCut() {
		
	}
	
	@Before("pointCut()")
	public void before() {
		System.out.println("before方法执行前");
	}
	
	@After("pointCut()")
	public void after() {
		System.out.println("after方法执行后");
	}
	
	@Around("pointCut()")
	public Object around(JoinPoint jointPoint) {
		ProceedingJoinPoint pj = (ProceedingJoinPoint)jointPoint;
		System.out.println("执行前，方法名称=" + pj.getSignature().getName());
		Object result = null;
		try {
			result = pj.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("执行后，方法名称=" + pj.getSignature().getName() + "   结果=" + result);
		return result;
	}
}
