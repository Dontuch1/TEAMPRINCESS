package com.princess.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.princess.domain.Member;
import com.princess.service.MypageService;

@Controller
@RequestMapping("/mypage/")
public class MyPageController {

	@Autowired
	MypageService myService;

	@RequestMapping("/myPageMain")
	public void myPageMain(HttpServletRequest request) {
		HttpSession session=request.getSession();
		session.setAttribute("id", "jojo");
	}

	@GetMapping("/myDetails")
	public void myDetails(Model model, Member member) {
		member.setId("jojo");
		model.addAttribute("member", myService.getMember(member));

	}

	@PostMapping("/updateDetails")
	public String updateDetails(Member member) {
		myService.updateMember(member);
		return "mypage/myDetails";
	}

	@GetMapping("/myPostList")
	public void myPostList() {

	}

	@GetMapping("/myProductList")
	public void myProductList() {

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
		return "forward:myDeposit";
	}
}
