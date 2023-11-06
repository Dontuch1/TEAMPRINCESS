package com.princess.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import com.princess.domain.CheckCondition.Display;
import com.princess.domain.CheckCondition.Rating;
import com.princess.domain.CheckCondition.YorN;
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

		model.addAttribute("boardList", myService.getBoardList(pageable, member));
		model.addAttribute("reviewList", myService.getReviewList(pageable, member));
		model.addAttribute("productList", myService.getProductList(member));
		model.addAttribute("memberPage", myService.getMember(member));
	}

	@GetMapping("/myDetails")
	public void myDetails(Model model, Member member, @AuthenticationPrincipal SecurityUser securityUser) {

		member.setId(securityUser.getUsername());
		model.addAttribute("loginUser", myService.getMember(member));
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
		model.addAttribute("userPage",myService.getMember(member));
	}

	@GetMapping("/myProductList")
	public void myProductList(Model model, @RequestParam(name = "id") String id, Product product, Member member) {
		member.setId(id);
		List<Product> productList = myService.getProductList(member);

		List<Product> ingProduct = new ArrayList<Product>();
		List<Product> standByProduct = new ArrayList<Product>();
		List<Product> reportedProduct = new ArrayList<Product>();
		Map<Product, String[]> soldProduct = new LinkedHashMap<Product, String[]>();

		for (Product pro : productList) {
			// 게시중단 상품
			if (pro.getDisplay().equals(Display.H)) {
				reportedProduct.add(pro);
			} else {
				// 판매중 / 천둥맨 대기중 / 판매완료
				if (pro.getSold().equals(YorN.N)) {
					ingProduct.add(pro);
				} else {
					// delivery=n면 판완
					if (pro.getDelivery().equals(YorN.N)) {
						String buyer;
						if (myService.isReviewed(pro, pro.getSalesId().getId(), myService.buyerId(pro))) {// 리뷰 써짐
							buyer = "";

						} else {// 리뷰 안써짐
							buyer = myService.buyerId(pro);
						}
						String[] str = { buyer, "" };
						soldProduct.put(pro, str);

					} else {// sold면서 delivery=y
						if (myService.thunderId(pro).isBlank()) {
							standByProduct.add(pro);
						} else {
							String buyer;
							String thunder;
							if (myService.isReviewed(pro, pro.getSalesId().getId(), myService.buyerId(pro))) {
								buyer = "";
							} else {
								buyer = myService.buyerId(pro);
							}
							if (myService.isReviewed(pro, pro.getSalesId().getId(), myService.thunderId(pro))) {
								thunder = "";
							} else {
								thunder = myService.thunderId(pro);

							}
							String[] str = { buyer, thunder };
							soldProduct.put(pro, str);

						}
					}
				}
			}

		}
		model.addAttribute("ingProduct", ingProduct);
		model.addAttribute("standByProduct", standByProduct);
		model.addAttribute("soldProduct", soldProduct);
		model.addAttribute("reportedProduct", reportedProduct);
		model.addAttribute("userPage",myService.getMember(member));

	}

	@GetMapping("/myReviewList")
	public void myReviewList(Model model, @RequestParam(name = "id") String id, Review review, Member member,
			@PageableDefault(page = 0, size = 5, sort = "pNo", direction = Sort.Direction.DESC) Pageable pageable) {
		member.setId(id);
		Page<Review> reviewListPage = myService.getReviewList(pageable, member);
		int nowPage = reviewListPage.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 5, reviewListPage.getTotalPages());
		model.addAttribute("reviewList", reviewListPage);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);

		Page<Review> sentReviewListPage = myService.getSentReviewList(pageable, id);
		int nowPage1 = reviewListPage.getPageable().getPageNumber() + 1;
		int startPage1 = Math.max(nowPage1 - 4, 1);
		int endPage1 = Math.min(nowPage1 + 5, reviewListPage.getTotalPages());
		model.addAttribute("sentReviewList", sentReviewListPage);
		model.addAttribute("nowPage1", nowPage1);
		model.addAttribute("startPage1", startPage1);
		model.addAttribute("endPage1", endPage1);
		model.addAttribute("userPage", myService.getMember(member));
	}

	@GetMapping("/myWishList")
	public void myWishList(Model model, Member member, @AuthenticationPrincipal SecurityUser securityUser) {

		member.setId(securityUser.getUsername());
		model.addAttribute("wishList", myService.getLikeWishList(member));

	}

	@GetMapping("/myBuyList")
	public void myBuyList(Model model, Member member, @AuthenticationPrincipal SecurityUser securityUser) {

		member.setId(securityUser.getUsername());
		model.addAttribute("buyList", myService.getBuyList(member));

	}

	@GetMapping("/myDeposit")
	public void myDeposit(Model model, Member member, @AuthenticationPrincipal SecurityUser securityUser) {

		member.setId(securityUser.getUsername());
		model.addAttribute("loginUser", myService.getMember(member));
	}

	@PostMapping("/updateDeposit")
	public String updateDeposit(Member member) {

		myService.updateDeposit(member);
		return "redirect:myDeposit";
	}

	@PostMapping("/insertReview")
	public String insertReview(Review review, @AuthenticationPrincipal SecurityUser securityUser,
			@RequestParam(name = "rating") String rating, @RequestParam("receiver") String receiver, Product product) {

		review.setSender(securityUser.getUsername());
		review.setPNo(product);
		Member mem = new Member();
		mem.setId(receiver);
		review.setReceiver(mem);
		mem = myService.getMember(mem);

		if (rating.equals("UP")) {
			review.setReview(Rating.UP);
			// 배터리 올리기
			mem.setBattery(mem.getBattery() + 1);
		} else {
			review.setReview(Rating.DOWN);
			// 배터리 내리기
			mem.setBattery(mem.getBattery() - 1);
		}
		myService.insertReview(review, product);
		if (mem.getBattery() <= 0) {
			myService.disalbeMember(mem);
		} else {
		myService.updateBattery(mem);
		}

		return "redirect:myBuyList";
	}
	@PostMapping("/sendReview")
	public String sendReview(Review review, @AuthenticationPrincipal SecurityUser securityUser,
			@RequestParam(name = "rating") String rating, @RequestParam("receiver") String receiver, Product product) {
		System.out.println("컨트롤러 product: "+product.toString());
		System.out.println("컨트롤러 rating: "+rating);
		System.out.println("컨트롤러 receiver: "+receiver);
		review.setSender(securityUser.getUsername());
		review.setPNo(product);
		Member mem = new Member();
		mem.setId(receiver);
		review.setReceiver(mem);
		mem = myService.getMember(mem);

		if (rating.equals("UP")) {
			review.setReview(Rating.UP);
			// 배터리 올리기
			mem.setBattery(mem.getBattery() + 1);
		} else {
			review.setReview(Rating.DOWN);
			// 배터리 내리기
			mem.setBattery(mem.getBattery() - 1);
		}
		myService.insertReview(review, product);
		if (mem.getBattery() <= 0) {
			myService.disalbeMember(mem);
		} else {
		myService.updateBattery(mem);
		}

		return "redirect:myProductList?id="+securityUser.getUsername();
	}
}
