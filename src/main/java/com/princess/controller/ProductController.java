package com.princess.controller;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;

import com.princess.config.SecurityUser;
import com.princess.domain.Auction;
import com.princess.domain.CheckCondition.YorN;
import com.princess.domain.Member;
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
			@PageableDefault(page = 0, size = 12, sort = "pNo", direction = Sort.Direction.DESC) Pageable pageable) {
		if (search.getSearchCondition() == null)
			search.setSearchCondition("TITLE");
		if (search.getSearchKeyword() == null)
			search.setSearchKeyword("");

		Page<Product> productList = productService.getProductList(type, search, pageable);

		for (Product prod : productList) {
			if (prod.getAuction().equals(YorN.Y)) {
				Date now = new Date();
				Date regdate = prod.getRegdate();
				Instant instant = now.toInstant(); // Date를 Instant로 변환
				Instant instant2 = regdate.toInstant();
				LocalDateTime localRegdate = instant2.atZone(ZoneId.systemDefault()).toLocalDateTime();
				LocalDateTime localNow = instant.atZone(ZoneId.systemDefault()).toLocalDateTime(); // Instant를
																									// LocalDateTime로 변환
				LocalDateTime expiredTime = localRegdate.plusDays(prod.getAucDuration()); // 7일 전 날짜 계산
				if (expiredTime.isBefore(localNow))
					prod.setSold(YorN.Y);
					productService.updateProduct(prod);
			}
		}

		int nowPage = productList.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 5, productList.getTotalPages());

		model.addAttribute("productList", productList);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("productList", productList);
		return "product/getProductList";

	}

	@GetMapping("/getProduct")
	public String getProduct(Product product, Model model, @AuthenticationPrincipal SecurityUser securityUser) {
		product = productService.getProduct(product);
		model.addAttribute("product", product);
		System.out.println(product.toString());
		Auction auc = new Auction();
		if (product.getAuction().equals(YorN.Y)) {
			if (productService.getAuctionMaxPrice(product) == null)
				auc.setAuctionPrice(product.getPrice());
			else 
				auc = (productService.getAuctionMaxPrice(product));
			List<Auction> auctionList = productService.getAuctionList(product);
			model.addAttribute("auctionList", auctionList);
			int auctionCnt = productService.getAuctionCnt(product, securityUser.getUsername());
			model.addAttribute("auctionCnt", auctionCnt);
		}
		model.addAttribute("auction", auc);
		return "product/getProduct";
	}

	@GetMapping("/insertProduct")
	public void insertProduct() {

	}

	@PostMapping("/insertProduct")
	public String insertProduct(Product product, @RequestParam MultipartFile file,
			@RequestParam("strPrice") String strPrice) {
		product.setPrice(Integer.parseInt(strPrice.replaceAll("^0+", "")));
		System.out.println("price : " + product.getPrice());
		productService.insertProduct(product, file);
		if (product.getAuction().equals(YorN.Y)) {
			return "redirect:getProductList?type=Auc";
		} else
			return "redirect:getProductList?type=prod";
	}

	@GetMapping("/deleteProduct")
	public String deleteProduct(Product product, @RequestParam String isAuc) {
		productService.deleteProduct(product);
		if (isAuc.indexOf(YorN.Y.toString()) != -1)
			return "forward:getProductList?type=Auc";
		else
			return "forward:getProductList?type=prod";
	}
	
	@PostMapping("/buyProduct")
	public String buyProduct(Product product, @RequestParam String id) {
		product = productService.getProduct(product);
		if (product.getDelevery().equals(YorN.Y)) {
			Member buyer = new Member();
			buyer.setId(id);
			buyer = productService.getMember(buyer);
			buyer.setDeposit(buyer.getDeposit()-product.getPrice()-1500);
			productService.setMemberDepoist(buyer);
		}
		productService.buyProduct(product, id);
		return "redirect:/mypage/myBuyList";
	}
	
	@PostMapping("/bidProduct")
	public String bidProduct(Product product, @RequestParam String id, @RequestParam int bid) {
		product = productService.getProduct(product);
		Member buyer = new Member();
		buyer.setId(id);
		buyer = productService.getMember(buyer);
		if (productService.getBidList(buyer) != null) {
			
		}
		
			if (product.getDelevery().equals(YorN.Y)) {
				buyer.setDeposit(buyer.getDeposit() - bid - 1500);
			} else buyer.setDeposit(buyer.getDeposit() - bid);
		productService.setMemberDepoist(buyer);
		
		productService.insertAuction(product, id, bid);
		return "redirect:getProduct?pNo=" + product.getPNo();
	}
}
