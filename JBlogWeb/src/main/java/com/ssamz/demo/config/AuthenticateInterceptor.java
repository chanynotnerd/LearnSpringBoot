package com.ssamz.demo.config;

import org.springframework.web.servlet.HandlerInterceptor;

import com.ssamz.demo.domain.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AuthenticateInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		// preHandle : 요청을 처리하기 전에 실행되는 코드 작성
		// 세션에 회원 정보가 있는지 확인
		HttpSession session = request.getSession();
		
		User principal = (User) session.getAttribute("principal");
		if(principal == null)
		{
			response.sendRedirect("/auth/login");
		}
		return true;
	}

}
