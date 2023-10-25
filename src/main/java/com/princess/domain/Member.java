package com.princess.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import com.princess.domain.CheckCondition.Role;
import com.princess.domain.CheckCondition.YorN;

import lombok.Data;
import lombok.ToString;

@DynamicInsert 
@Data
@ToString (exclude = {"productList", "boardList", "auctionList", "salesList", "reviewList", "likeWishList", "reportList", "replyList"})
@Entity
public class Member {
	
	@Id
	private String id;
	
	@Column(nullable = false)
	private String nickName;
	
	@Column(nullable = false)
	private String location;
	
	
	private int battery = 50;
	
	@Column(nullable = false)
	private String userName;
	
	@Column(nullable = false)
	private String birth;
	
	@Column(nullable = false)
	private String phone;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String email;
	
	@Enumerated(EnumType.STRING)
	private YorN agree;
	
//	@ColumnDefault("'1'")
	private int deposit = 0;
	
	@Temporal(TemporalType.DATE)
	@ColumnDefault("sysdate")
	private Date regdate = new Date();
	
	@ColumnDefault("'MEMBER'")
	@Enumerated(EnumType.STRING)
	private Role auth;
	
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
	
}
