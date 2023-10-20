package com.princess.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system")
public class SystemController{
	
	@RequestMapping("/login")
	public void login() {
		
	}
	
	@PostMapping("/register")
	public void register() {
		
		
		
	}
	
	@GetMapping("/register")
	public String registerView() {
		return "forward:register";
	}
	
}