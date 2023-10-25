package com.princess.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.princess.config.PrincessUserDetailsService;
import com.princess.config.SecurityUser;
import com.princess.domain.Member;
import com.princess.service.MypageService;

@Controller
@RequestMapping("/mypage/")
public class MyPageController {

	@Autowired
	MypageService myService;

	

	@RequestMapping("/myPageMain")
	public void myPageMain() {

	}

	@GetMapping("/myDetails")
	public void myDetails(Model model, Member member, @AuthenticationPrincipal SecurityUser securityUser) {
		member.setId(securityUser.getUsername());
		model.addAttribute("loginUser", myService.getMember(member));
		System.out.println("마페컨트롤러/마이서비스" + myService.getMember(member).toString());
	}

	@PostMapping("/updateDetails")
	public String updateDetails(Member member) {
		myService.updateMember(member);
		return "redirect:myDetails";
	}

	@GetMapping("/myPostList")
	public void myPostList() {

	}

	@GetMapping("/myProductList")
	public void myProductList(Model model, Principal principal) {

	}

	@GetMapping("/myReviewList")
	public void myReviewList() {

	}

	@GetMapping("/myWishList")
	public void myWishList() {

	}

	@GetMapping("/myDeposit")
	public void myDeposit() {

	}

	@PostMapping("/updateDeposit")
	public String updateDeposit(Member member) {
		myService.updateMember(member);
		return "/mypage/myDeposit";
	}
}
