package com.princess.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

	@GetMapping("/system/login")
	public void login() {
	}
	@GetMapping("/admin/loginview")
	public void loginview() {
	}
}