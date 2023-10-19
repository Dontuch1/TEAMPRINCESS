package com.princess.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = {"auctionNo", "auctionId"})
@Entity
public class Auction {
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID", nullable = false, updatable = false)
	private Member auctionId;
	
	@ManyToOne
	@JoinColumn(name = "PRODUCT_PNO", nullable = false, updatable = false)
	private Product auctionNo;
	
	private int auctionPrice;
	
	private Date regdate = new Date();
	
	
	// 연관관계 설정
	
	public void setAuctionId(Member id) {
		this.auctionId = id;
		auctionId.getAuctionList().add(this);
	}
	
	public void setAuctionNo(Product pNo) {
		this.auctionNo = pNo;
		auctionNo.getAuctionList().add(this);
	}
	
}
