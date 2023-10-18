package com.princess.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product/")
public class ProductController {

	@RequestMapping("/getProductList")
	public void getProductList() {
		
	}
	
	@GetMapping("/getProduct")
	public void getProduct() {
		
	}
	
	@GetMapping("/insertProduct")
	public void insertProduct() {
		
	}
	
}
