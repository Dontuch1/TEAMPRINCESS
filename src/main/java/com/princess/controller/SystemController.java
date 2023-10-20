package com.princess.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/System/")
public class SystemController {


	
	@GetMapping("/loginView")
	public void loginView() {
	}
	@PostMapping("/login")
	public void login() {
		
	}
	
	@GetMapping("/register")
	public void register() {
		
	}
}