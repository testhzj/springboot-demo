package com.gee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableAspectJAutoProxy
@ServletComponentScan
@MapperScan("com.gee.mapper")
public class Config {
	public static void main(String args[]) {
		SpringApplication.run(Config.class, args);
	}
}
