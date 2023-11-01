package com.princess.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.princess.domain.Auction;
import com.princess.domain.Member;
import com.princess.domain.Product;
import com.princess.domain.Search;

public interface ProductService {

	void insertProduct(Product prodcut, MultipartFile file);

	void updateProduct(Product product);

	void deleteProduct(Product product);

	Product getProduct(Product product);

	Page<Product> getProductList(String type, Search search, Pageable pageable);
	
	Page<Product> myThunderList(Search search, Pageable pageable);
	
	List<Auction> getAuctionList(Product product);
	
	Auction getAuctionMaxPrice(Product product);
	
	void buyProduct(Product product, String buyer);
	
	int getAuctionCnt(Product product, String id);
	
	void insertAuction(Product product, String id, int bid);
	
	void setMemberDepoist(Member member);
	
	Member getMember(Member member);
	
	List<Auction> getBidList(Member member, Product product);
}