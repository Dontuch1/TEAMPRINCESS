package com.princess.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.princess.domain.Member;
import com.princess.service.MemberService;

@Controller
@RequestMapping("/admin/")
public class AdminController {

	
	@Autowired
	MemberService memberService;
	
	
	@RequestMapping("/adminMain")
	public void adminMain() {

	}
	@RequestMapping("/deleteMember")
	public String deleteMember(Member member,@RequestParam String id) {
		member.setId(id);
		memberService.deleteMember(member);
		return "redirect:getMemberList";
	}

	@GetMapping("/getMemberList")
	public void getMemberList(Member member, Model model) {
		
		model.addAttribute("memberList",memberService.getMemberList(member));
	}

	@GetMapping("/getReportList")
	public void getReportList() {
	}
	
	@GetMapping("/getReportList2")
	public void getReportList2() {
	}
	
	

	
	// 로그아웃
	@RequestMapping("/logout")
	public void logout() {

	}
}