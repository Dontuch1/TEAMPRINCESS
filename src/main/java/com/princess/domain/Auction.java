package com.princess.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;

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
	
	@Column(nullable = false)
	private int auctionPrice;
	
	@Temporal(TemporalType.DATE)
	@ColumnDefault("sysdate")
	private Date regdate;
	
	
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
