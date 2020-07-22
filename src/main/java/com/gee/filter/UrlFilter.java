package com.gee.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

@WebFilter(urlPatterns = "/test/hello")
public class UrlFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("init filter=" + 		this.getClass().getName());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String token = request.getParameter("token");
		if(StringUtils.isEmpty(token)) {
			return;
		}
		System.out.println("do filter pre=" + 		this.getClass().getName());
		chain.doFilter(request, response);
		System.out.println("do filter post=" + 		this.getClass().getName());

	}

	@Override
	public void destroy() {
		System.out.println("destroy filter=" + 		this.getClass().getName());		
	}

}
