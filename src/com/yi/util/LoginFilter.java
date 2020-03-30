package com.yi.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		//session -> httpservletrequest에 있다. 
		//servletRequest의 자식 
		HttpSession session = req.getSession();
		if(session.getAttribute("Auth")==null) {
			//auth키가 없으면 로그인이 안되어 있음 -> 로그인 화면으로 이동
			HttpServletResponse res = (HttpServletResponse) response;
			res.sendRedirect("login.do");
		}else {
			//auth 키가 있으면 다음 필터를 거쳐서 이동
			chain.doFilter(request, response);
			
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
