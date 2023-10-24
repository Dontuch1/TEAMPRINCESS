package com.princess.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.princess.domain.Member;
import com.princess.service.MemberSerivce;

@Controller
public class SystemController{
	
	@Autowired
	MemberSerivce memberService;
	
	@GetMapping("/system/register")
	public void register() {
		
	}
	
	@PostMapping("/system/register")
	public String register(Member member) {
		memberService.insertMember(member);
		return "forward:login";
	}
	
}