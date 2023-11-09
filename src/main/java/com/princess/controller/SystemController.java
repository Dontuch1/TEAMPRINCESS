package com.princess.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.princess.domain.Member;
import com.princess.service.MemberService;
import com.princess.service.ThunderService;

@Controller
@RequestMapping("/system/")
public class SystemController {

	@Autowired
	private MemberService memberService;

<<<<<<< HEAD
	@RequestMapping("/accessDenied")
	public void accessDenied() {

	}
=======
	@Autowired
	private ThunderService thunderservice;
>>>>>>> refs/heads/ganji

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
	public String register(Member member, @RequestParam String gu, @RequestParam String dong) {
		member.setLocation(gu + "구 " + dong + "동");
		System.out.println(member.toString());

		memberService.insertMember(member);
		return "redirect:login";
	}
	
	@PostMapping("/transformThunder")
	public String transformThunder(@RequestBody Map<String, Object> payload, Member member) {
		System.out.println("천둥맨 변신 중");
		member.setId((String)payload.get("memberThunderId"));
		System.out.println(member.getId());
		thunderservice.updateThunder(member);
		return "redirect:/product/getProductList?type=prod";
	}

}