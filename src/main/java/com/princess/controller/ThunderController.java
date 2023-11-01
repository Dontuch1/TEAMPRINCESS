package com.princess.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.princess.domain.Product;
import com.princess.domain.Search;
import com.princess.service.ProductService;

@Controller
@RequestMapping("/thunder")
public class ThunderController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/myThunderList")
	public String myThunderList(Model model, Search search,
			@PageableDefault(page = 0, size = 5, sort = "pNo", direction = Sort.Direction.DESC) Pageable pageable) {
		if (search.getSearchCondition() == null)
			search.setSearchCondition("TITLE");
		if (search.getSearchKeyword() == null)
			search.setSearchKeyword("");
		
		Page<Product> thunderList = productService.myThunderList(search, pageable);
		
		int nowPage = thunderList.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 5, thunderList.getTotalPages());
		
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("thunderList1", thunderList);
		return "thunder/myThunderList";
	}
	
	@GetMapping("/standByList")
	public void standByList() {
		
	}
	
}
