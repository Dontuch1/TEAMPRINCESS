package com.princess.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	public void myPageMain() {

	}

	@GetMapping("/myDetails")
	public void myDetails() {

	}

	@PostMapping("/updateDetails")
	public String updateDetails(Member member) {
		myService.updateMember(member);
		return "forward:myDetails";
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
