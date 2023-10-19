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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.princess.domain.CheckCondition.YorN;
import com.princess.domain.CheckCondition.pCategory;

import com.princess.domain.CheckCondition.display;

import lombok.Data;
import lombok.ToString;

@Data
@ToString (exclude = {"salesId", "auctionList", "salesList", "reviewList"})
@Entity
public class Product {
	
	@Id @GeneratedValue
	@Column (name = "PNO")
	private Long pNo;
	
	@Enumerated(EnumType.STRING)
	private YorN auction;
	
	@Enumerated(EnumType.STRING)
	private pCategory pCategory;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID", nullable = false, updatable = false)
	private Member salesId;
	
	private String title;
	
	private String content;
	
	private int price;
	
	private String upload;
	
	@Enumerated(EnumType.STRING)
	private YorN sold;
	
	private Date AucDuration;
	
	@Enumerated(EnumType.STRING)
	private YorN delevery;
	
	@Enumerated(EnumType.STRING)
	private display display;
	
	private Date regdate = new Date();
	
	// 연관관계 설정
	
	@OneToMany(mappedBy = "PNO") // Auction
	private List<Auction> auctionList = new ArrayList<Auction>();
	
	@OneToMany(mappedBy = "PNO") // Sales
	private List<Sales> salesList = new ArrayList<Sales>();
	
	@OneToMany(mappedBy = "PNO") // Review
	private List<Review> reviewList = new ArrayList<Review>();
	
	public void setSalesId(Member id) { // Member
		this.salesId = id;
		salesId.getProductList().add(this);
	}
}
