package com.princess.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.ToString;

@Data
@ToString (exclude = {"productList", "boardList", "auctionList", "salesList", "reviewList", "likeWishList", "reportList", "replyList"})
@Entity
public class Member {
	
	@Id @GeneratedValue
	private String id;
	
	// 연관관계 설정
	
	@OneToMany(mappedBy = "id") // Product
	private List<Product> productList = new ArrayList<Product>();
	
	@OneToMany(mappedBy = "id") // Board
	private List<Board> boardList = new ArrayList<Board>();
	
	@OneToMany(mappedBy = "id") // Auction
	private List<Auction> auctionList = new ArrayList<Auction>();
	
	@OneToMany(mappedBy = "id") // Sales
	private List<Sales> salesList = new ArrayList<Sales>();
	
	@OneToMany(mappedBy = "id") // Review
	private List<Review> reviewList = new ArrayList<Review>();
	
	@OneToMany(mappedBy = "id") // LikeWish
	private List<LikeWish> likeWishList = new ArrayList<LikeWish>();
	
	@OneToMany(mappedBy = "id") // Report
	private List<Report> reportList = new ArrayList<Report>();
	
	@OneToMany(mappedBy = "id") // Reply
	private List<Reply> replyList = new ArrayList<Reply>();
}
