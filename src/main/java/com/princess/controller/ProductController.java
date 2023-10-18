package com.princess.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.princess.domain.Product;

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

	@PostMapping("/insertProduct")
	public String insertProduct(Product product) {
		return "redirect:product/getProductList";
	}

	@GetMapping("/getAuction")
	public void getAuction() {

	}

}
