package com.princess.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.princess.domain.Member;
import com.princess.persistence.MemberRepository;

@ControllerAdvice
public class UserControllerAdvice {

	@Autowired
	MemberRepository memberRepo;

	@ModelAttribute("currentUser")
	public Member getCurrentUser(@AuthenticationPrincipal User user, HttpServletRequest request) {
		 if (!shouldSkipAdvice(request.getRequestURI())) {
	            return memberRepo.findById(user.getUsername()).get();
	        }
	        return null;
	}

	private boolean shouldSkipAdvice(String requestUri) {
		// 여기에 특정 페이지 또는 URL 패턴에 대한 조건을 추가
		// 조건을 만족하는 경우 advice를 스킵하고, 그렇지 않은 경우 실행
		if (requestUri.equals("/system/login")||requestUri.equals("/system/register")) {
			return true;
		}
		return false;
	}
}