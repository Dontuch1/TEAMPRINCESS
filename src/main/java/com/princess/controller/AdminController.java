package com.princess.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/")
public class AdminController {

	@RequestMapping("/adminMain")
	public void foradmin() {
		System.out.println("admin 요청입니다");
	}
	
	@GetMapping("/getMemberList")
	public void getMemberList() {
		
	}
	
	@GetMapping("/getReportList")
	public void getReportList() {
		
	}
}