package com.princess.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;

import com.princess.domain.CheckCondition.YorN;

import lombok.Data;
import lombok.ToString;

@Data
@ToString (exclude = {"productList", "boardList", "auctionList", "salesList", "reviewList", "likeWishList", "reportList", "replyList", "authList"})
@Entity
public class Member {
	
	@Id @GeneratedValue
	private String id;
	
	@Column(nullable = false)
	private String nickName;
	
	@Column(nullable = false)
	private String location;
	
	@Column(nullable = false)
	@ColumnDefault("50")
	private int battry;
	
	@Column(nullable = false)
	private String userName;
	
	@Column(nullable = false)
	private Date birth;
	
	@Column(nullable = false)
	private String phone;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String email;
	
	@Enumerated(EnumType.STRING)
	private YorN agree;
	
	@Column(nullable = false)
	@ColumnDefault("0")
	private int deposit;
	
	private Date regdate = new Date();
	
	// 연관관계 설정
	
	@OneToMany(mappedBy = "salesId") // Product
	private List<Product> productList = new ArrayList<Product>();
	
	@OneToMany(mappedBy = "userId") // Board
	private List<Board> boardList = new ArrayList<Board>();
	
	@OneToMany(mappedBy = "auctionId") // Auction
	private List<Auction> auctionList = new ArrayList<Auction>();
	
	@OneToMany(mappedBy = "salesId") // Sales
	private List<Sales> salesList = new ArrayList<Sales>();
	
	@OneToMany(mappedBy = "reviewId") // Review
	private List<Review> reviewList = new ArrayList<Review>();
	
	@OneToMany(mappedBy = "likeId") // LikeWish
	private List<LikeWish> likeWishList = new ArrayList<LikeWish>();
	
	@OneToMany(mappedBy = "rptId") // Report
	private List<Report> reportList = new ArrayList<Report>();
	
	@OneToMany(mappedBy = "userId") // Reply
	private List<Reply> replyList = new ArrayList<Reply>();
	
	@OneToMany(mappedBy = "userId") // Auth
	private List<Auth> authList = new ArrayList<Auth>();
}
