package com.princess.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.princess.domain.Product;
import com.princess.domain.Search;
import com.princess.service.ProductService;

@Controller
@RequestMapping("/product/")
public class ProductController {
	
	@Autowired
	ProductService productService;

	@RequestMapping("/getProductList")
	public String getProductList(Model model, Search search) {
		if (search.getSearchCondition() == null)
			search.setSearchCondition("TITLE");
		if (search.getSearchKeyword() == null)
			search.setSearchKeyword("");
		
		Page<Product> productList = productService.getProductList(search);
		model.addAttribute("productList", productList);
		return "product/getProductList";
	}
	
	@GetMapping("/getProduct")
	public void getProduct() {

	}

	@GetMapping("/insertProduct")
	public void insertProduct() {
		
	}

	@PostMapping("/insertProduct")
	public String insertProduct(Product product, @RequestParam MultipartFile file) {
		productService.insertProduct(product, file);
		return "redirect:getProductList";
	}

	@GetMapping("/getAuction")
	public void getAuction() {

	}

}
