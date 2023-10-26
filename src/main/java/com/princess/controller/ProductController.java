package com.princess.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.princess.domain.CheckCondition.YorN;
import com.princess.domain.Product;
import com.princess.domain.Search;
import com.princess.service.ProductService;

@Controller
@RequestMapping("/product/")
public class ProductController {

	@Autowired
	ProductService productService;

	@RequestMapping("/getProductList")
	public String getProductList(@RequestParam String type, Model model, Search search,
			@PageableDefault(page = 0, size = 5, sort = "pNo", direction = Sort.Direction.DESC) Pageable pageable) {
		if (search.getSearchCondition() == null)
			search.setSearchCondition("TITLE");
		if (search.getSearchKeyword() == null)
			search.setSearchKeyword("");

		Page<Product> productList = productService.getProductList(type, search, pageable);

		int nowPage = productList.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 5, productList.getTotalPages());
		System.out.println(productList);
		System.out.println(nowPage);
		System.out.println(startPage);
		System.out.println(endPage);

		model.addAttribute("productList", productList);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("productList", productList);
		return "product/getProductList";

	}

	@GetMapping("/getProduct")
	public String getProduct(Product product, Model model) {
		model.addAttribute("product", productService.getProduct(product));
		System.out.println(productService.getProduct(product).toString());
		return "product/getProduct";
	}

	@GetMapping("/insertProduct")
	public void insertProduct() {

	}

	@PostMapping("/insertProduct")
	public String insertProduct(Product product, @RequestParam MultipartFile file,
			@RequestParam("strPrice") String strPrice) {
		product.setPrice(Integer.parseInt(strPrice));
		System.out.println(product.toString());
		productService.insertProduct(product, file);
		if (product.getAuction().equals(YorN.Y)) {
			return "redirect:getProductList?type=Auc";
		} else
			return "redirect:getProductList?type=prod";
	}

	@GetMapping("/deleteProduct")
	public String deleteProduct(Product product) {
		productService.deleteProduct(product);
		return "forward:getProductList?type=prod";
	}
}
