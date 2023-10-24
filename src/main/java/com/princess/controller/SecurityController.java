package com.princess.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
	
	@GetMapping("/system/login")
	public void login() {
		
	}
	
	@GetMapping("/system/accessDenied")
	public void accessDenied() {
		
	}
	
	@GetMapping("/admin/adminPage")
	public void forAdmin() {
		System.out.println("Admin 요청입니다.");
	}
}
