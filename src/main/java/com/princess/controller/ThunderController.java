package com.princess.controller;

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
import com.princess.domain.Sales;
import com.princess.domain.Search;
import com.princess.service.MemberService;
import com.princess.service.ProductService;
import com.princess.service.ThunderService;

@Controller
@RequestMapping("/thunder")
public class ThunderController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private MemberService memberService; 
	
	@Autowired
	private ThunderService thunderservice;
	
	@RequestMapping("/myThunderList")
	public String myThunderList(@AuthenticationPrincipal SecurityUser securityUser, Model model, Search search, Member member,
			@PageableDefault(page = 0, size = 7, sort = "pNo", direction = Sort.Direction.DESC) Pageable pageable) {
		if (search.getSearchCondition() == null)
			search.setSearchCondition("TITLE");
		if (search.getSearchKeyword() == null)
			search.setSearchKeyword("");
		
	// sales 시작	
		Sales sales = new Sales();
		
		member.setId(securityUser.getUsername());
		sales.setBuyer(member);
		System.out.println(sales.toString());
	// sales 끝
		
		Page<Product> thunderList = thunderservice.myThunderList(search, pageable, member);
		
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
		memberService.deleteThunder(member);
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
	public void standByList() {
		
	}
	
}
