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
import com.princess.domain.CheckCondition.Type;
import com.princess.domain.CheckCondition.YorN;
import com.princess.domain.LikeWish;
import com.princess.domain.Member;
import com.princess.domain.Product;
import com.princess.domain.Report;
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
		System.out.println("search : " + search.toString());
		System.out.println("type : " + type.toString());
		
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
		model.addAttribute("type", type);
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
		}
		model.addAttribute("auction", auc);
		
		Auction currBid = new Auction();
		System.out.println("currbid-raw : " + productService.getAuctionList(product));
		if (!productService.getAuctionList(product).isEmpty())
			productService.getAuctionList(product).get(0);
		System.out.println("currBid : " + currBid.toString());
		model.addAttribute("currBid", currBid);
		
		List<Auction> bidList = productService.getBidList(securityUser.getMember(), product);
		model.addAttribute("bidList", bidList);
		
		int auctionCnt = productService.getAuctionCnt(product, securityUser.getUsername());
		model.addAttribute("auctionCnt", auctionCnt);
		
		int wishCnt = productService.countWishes(product, Type.PRODUCT);
		model.addAttribute("wishCnt", wishCnt);
		
		model.addAttribute("isWished", productService.isWished(securityUser.getUsername(), product, Type.PRODUCT));

		model.addAttribute("isReported", productService.isReported(securityUser.getMember(), product, Type.PRODUCT));
		System.out.println("isReproted : " + productService.isReported(securityUser.getMember(), product, Type.PRODUCT));
		return "product/getProduct";
	}

	@GetMapping("/insertProduct")
	public void insertProduct(Model model) {
		model.addAttribute("product", null);
	}

	@PostMapping("/insertProduct")
	public String insertProduct(Product product, @RequestParam MultipartFile file,
			@RequestParam("strPrice") String strPrice) {
		product.setPrice(Integer.parseInt(strPrice.replaceAll("^0+", "")));
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
		if (productService.getBidList(buyer, product) != null) {
			buyer.setDeposit(buyer.getDeposit() + productService.getBidList(buyer, product).get(0).getAuctionPrice());
		}
		if (product.getDelevery().equals(YorN.Y)) {
			buyer.setDeposit(buyer.getDeposit() - bid - 1500);
		} else buyer.setDeposit(buyer.getDeposit() - bid);
		productService.setMemberDepoist(buyer);
		productService.insertAuction(product, id, bid);
		return "redirect:getProduct?pNo=" + product.getPNo();
	}
	
	@GetMapping("/updateProduct")
	public void updateProduct(Model model, Product product) {
		System.out.println("get-pre-product : " + product);
		model.addAttribute("product", productService.getProduct(product));
		System.out.println("get-post-product : " + productService.getProduct(product));
	}
	
	@PostMapping("/updateProduct")
	public String updateProduct(Product product, @RequestParam String strPrice, @RequestParam MultipartFile file) {
		product.setPrice(Integer.parseInt(strPrice));
		System.out.println("post-product : " + product);
		System.out.println("file : file");
		productService.editProduct(product, file);
		return "redirect:getProduct?pNo=" + product.getPNo();
	}
	
	@GetMapping("/updateWish")
	public String updateWish(Product product, @AuthenticationPrincipal SecurityUser securityUser) {
		LikeWish likeWish = new LikeWish();
		Member member = new Member();
		member.setId(securityUser.getUsername());
		likeWish.setLikeId(member);
		System.out.println("update cont : " + productService.isWished(securityUser.getUsername(), product, Type.PRODUCT));
		if (!productService.isWished(securityUser.getUsername(), product, Type.PRODUCT)) {
			likeWish.setPNo(product.getPNo());
			likeWish.setType(Type.PRODUCT);
			productService.insertLike(likeWish);
		} else {
			productService.deleteLike(product, Type.PRODUCT, member);
		}
		return "redirect:getProduct?pNo=" + product.getPNo();
	}
	
	@GetMapping("/reportProduct")
	public String reportProduct(Report report, @RequestParam String id) {
		Member member = new Member();
		System.out.println("report : " + report);
		System.out.println("id : " + id);
		member.setId(id);
		report.setRptId(member);
		productService.insertReport(report);
		return "redirect:getProduct?pNo=" + report.getPostNo();
	}
}
