package com.princess.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class MyPageController {

	@RequestMapping("/myPageMain")
	public void myPageMain() {
		
	}
	
	@GetMapping("/myDetails")
	public void myDetails() {
		
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
	
}
