package com.princess.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.princess.domain.Member;

@Controller
@RequestMapping("/system")
public class SystemController{
	
	@RequestMapping("/login")
	public void login() {
		
	}
	
	@GetMapping("/register")
	public void register() {
		
	}
	
	//로그아웃
	@PostMapping("/logout")
		public String logout{
			
		}
	
	
	
	
	@PostMapping("/register")
	public String register(Member member) {
		return "forward:login";
	}
	
	
	
}