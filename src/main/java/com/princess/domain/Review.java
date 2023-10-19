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
@ToString (exclude = {"reviewId", "buyId", "pNo"})
@Entity
public class Review {
	
	@Id @GeneratedValue
	private Long reviewNo;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID", nullable = false, updatable = false)
	private Member reviewId;
	
	@ManyToOne
	@JoinColumn(name = "SALES_SALESID", nullable = false, updatable = false)
	private Sales buyId;
	
	@ManyToOne
	@JoinColumn(name = "PRODUCT_PNO", nullable = false, updatable = false)
	private Product pNo;
	
	private String content;
	
	private Date reviewRegdate = new Date();
	
	// 연관관계 설정
	
	public void getReviewId(Member id) { // Member
		this.reviewId = id;
		reviewId.getReviewList().add(this);
	}
	
	public void getBuyId(Sales salesId) { // Sales
		this.buyId = salesId;
		buyId.getReviewList().add(this);
	}
	
	public void getPNo(Product pNo) { // Product
		this.pNo = pNo;
		pNo.getReviewList().add(this);
	}
	
}
