package com.princess.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.princess.config.SecurityUser;
import com.princess.domain.Member;
import com.princess.domain.Product;
import com.princess.domain.Review;
import com.princess.domain.Sales;
import com.princess.domain.Search;
import com.princess.service.ThunderService;

@Controller
@RequestMapping("/thunder/")
public class ThunderController {
	
	@Autowired
	private ThunderService thunderservice;
		
	@RequestMapping("/myThunderList")
	public String myThunderList(@AuthenticationPrincipal SecurityUser securityUser, Model model, Search search,
			@PageableDefault(page = 0, size = 7, sort = "traNo", direction = Sort.Direction.DESC) Pageable pageable) {
		if (search.getSearchCondition() == null)
			search.setSearchCondition("TITLE");
		if (search.getSearchKeyword() == null)
			search.setSearchKeyword("");
		
		System.out.println("thunderList 찾기 전");
		Page<Sales> thunderList = thunderservice.myThunderList(search, pageable);
		System.out.println("thunderList : " + thunderList.toString());
		
		int nowPage = thunderList.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 5, thunderList.getTotalPages());
		
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("thunderList1", thunderList);
		return "thunder/myThunderList";
	}
	
	@PostMapping("/myThunderQualification")
	public String myThunderQualification(@RequestBody Map<String, Object> payload, Member member) {
		
		member.setId((String)payload.get("memberId"));
		System.out.println("member : "+member.toString());
		thunderservice.deleteThunder(member);
		return "redirect:/product/getProductList?type=prod";
	}
	
	@PostMapping("/thunderDelivery")
	public String thunderDelivery(@RequestBody Map<String, Object> payload, Member member, Product product) {
		Long productPno = Long.valueOf((String)payload.get("thunder"));
		member.setId((String)payload.get("id"));
		product.setPNo(productPno);
		System.out.println("productPno : "+productPno);
		System.out.println("thunderId : "+member.toString());
		thunderservice.thunderDelivery(productPno, member);
		return "redirect:/thunder/myThunderList";
	}
	
	@GetMapping("/standByList")
	public String standByList(Model model, Product product, Search search,
			@PageableDefault(page = 0, size = 7, sort = "pNo", direction = Sort.Direction.DESC) Pageable pageable) {
		if (search.getSearchCondition() == null)
			search.setSearchCondition("TITLE");
		if (search.getSearchKeyword() == null)
			search.setSearchKeyword("");
		
		List<Review> reviewList = thunderservice.getreviewList();	
		System.out.println(reviewList.toString());
		
		model.addAttribute("reviewList", reviewList);
		return "thunder/standByList";
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
