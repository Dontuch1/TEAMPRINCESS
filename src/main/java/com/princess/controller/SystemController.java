package com.princess.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.princess.domain.Member;
import com.princess.service.MemberService;

@Controller
@RequestMapping("/system/")
public class SystemController {
	
	@Autowired
	private MemberService memberService;

	

	@GetMapping("/logout")
	public void logout() {
	}

	@GetMapping("/adminPage")
	public void admin() {
	}

	@GetMapping("/register")
	public void register() {
	}

	@PostMapping("/register")
	 public String register(Member member, HashMap<String, String> requestBody ) {
	 String guInput = requestBody.get("guInput");
	 String dongInput = requestBody.get("dongInput");
	 
		member.setLocation(guInput+ " " +dongInput);
		System.out.println(member);
		memberService.insertMember(member);
		return "redirect:login";
	}
	
	@GetMapping("/accessDenied")
	public void accessDenied() {
	}
	
	@PostMapping("/transformThunder")
	public String transformThunder(@RequestBody Map<String, Object> payload, Member member) {
		System.out.println("천둥맨 변신 중");
		member.setId((String)payload.get("memberThunderId"));
		memberService.updateThunder(member);
		return "redirect:/product/getProductList?type=prod";
	}

}