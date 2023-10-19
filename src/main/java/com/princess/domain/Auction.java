package com.princess.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = {"pNo", "auctionId"})
@Entity
public class Auction {
	
	@Id @GeneratedValue
	private Long auctionNo;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID", nullable = false, updatable = false)
	private Member auctionId;
	
	@ManyToOne
	@JoinColumn(name = "PRODUCT_PNO", nullable = false, updatable = false)
	private Product pNo;
	
	private int auctionPrice;
	
	private Date regdate = new Date();
	
	
	// 연관관계 설정
	
	public void setAuctionId(Member id) { // Member
		this.auctionId = id;
		auctionId.getAuctionList().add(this);
	}
	
	public void setPNo(Product pNo) { // Product
		this.pNo = pNo;
		pNo.getAuctionList().add(this);
	}
	
}
