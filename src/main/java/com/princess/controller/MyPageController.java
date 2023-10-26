package com.princess.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.princess.config.SecurityUser;
import com.princess.domain.Board;
import com.princess.domain.Member;
import com.princess.domain.Product;
import com.princess.domain.Review;
import com.princess.service.MypageService;

@Controller
@RequestMapping("/mypage")
public class MyPageController {

	@Autowired
	MypageService myService;

	@RequestMapping("/myPageMain")
	public void myPageMain(Model model, @RequestParam(name = "id") String id, Member member,
			@PageableDefault(page = 0, size = 10, sort = "regdate", direction = Sort.Direction.DESC) Pageable pageable) {

		member.setId(id);

		model.addAttribute("userPage", myService.getMember(member));
		model.addAttribute("boardList", myService.getBoardList(pageable, member));
		model.addAttribute("reviewList", myService.getReviewList(pageable, member));
		model.addAttribute("productList", myService.getProductList(pageable, member));
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
	public void myPostList(Model model, @RequestParam(name = "id") String id, Board board, Member member,
			@PageableDefault(page = 0, size = 10, sort = "postNum", direction = Sort.Direction.DESC) Pageable pageable) {
		member.setId(id);
		Page<Board> boardList = myService.getBoardList(pageable, member);
		int nowPage = boardList.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 5, boardList.getTotalPages());

		model.addAttribute("boardList", boardList);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);

	}

	@GetMapping("/myProductList")
	public void myProductList(Model model, @RequestParam(name = "id") String id, Product product, Member member,
			@PageableDefault(page = 0, size = 8, sort = "pNo", direction = Sort.Direction.DESC) Pageable pageable) {
		member.setId(id);
		Page<Product> productList = myService.getProductList(pageable, member);
		int nowPage = productList.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 5, productList.getTotalPages());

		model.addAttribute("userPage", myService.getMember(member));
		model.addAttribute("productList", productList);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
	}

	@GetMapping("/myReviewList")
	public void myReviewList(Model model, @RequestParam(name = "id") String id, Review review, Member member,
			@PageableDefault(page = 0, size = 10, sort = "pNo", direction = Sort.Direction.DESC) Pageable pageable) {
		member.setId(id);
		Page<Review> reviewListPage = myService.getReviewList(pageable, member);
		int nowPage = reviewListPage.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 5, reviewListPage.getTotalPages());

		model.addAttribute("reviewList", reviewListPage);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
	}

	@GetMapping("/myWishList")
	public void myWishList(Model model, Member member, @AuthenticationPrincipal SecurityUser securityUser) {
		System.out.println("/myWishList에서 매개변수로 넘어온 멤버 //" + member.toString());
		member.setId(securityUser.getUsername());
		System.out.println("/myWishList에서 setId한 멤버 //" + member.toString());
		model.addAttribute("wishList", myService.getLikeWishList(member));

	}

	@GetMapping("/myBuyList")
	public void myBuyList(Model model, Member member, @AuthenticationPrincipal SecurityUser securityUser) {
		member.setId(securityUser.getUsername());
		System.out.println("/myBuyList에서 setId한 멤버 //" + member.toString());
		model.addAttribute("buyList", myService.getBuyList(member));

	}

	@GetMapping("/myDeposit")
	public void myDeposit(Model model, Member member, @AuthenticationPrincipal SecurityUser securityUser) {
		member.setId(securityUser.getUsername());
		model.addAttribute("loginUser", myService.getMember(member));
		System.out.println("myDeposit 컨트롤러 : " + myService.getMember(member));
	}

	@PostMapping("/updateDeposit")
	public String updateDeposit(Member member) {
		System.out.println("updateDeposit 컨트롤러 : " + member.toString());
		myService.updateDeposit(member);
		return "redirect:myDeposit";
	}
}
