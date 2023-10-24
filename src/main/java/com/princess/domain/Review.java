package com.princess.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;
import lombok.ToString;

import com.princess.domain.CheckCondition.Rating;;

@Data
@ToString (exclude = {"reviewId", "buyId", "pNo"})
@Entity
public class Review {
	// 푸시 돼라
	@Id @GeneratedValue
	private Long reviewNo;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID", nullable = false, updatable = false)
	private Member reviewId;
	
	@ManyToOne
	@JoinColumn(name = "SALES_SALES_ID", nullable = false, updatable = false)
	private Sales buyId;
	
	@ManyToOne
	@JoinColumn(name = "PRODUCT_PNO", nullable = false, updatable = false)
	private Product pNo;
	
	private String content;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private Rating review;
	
	@Temporal(TemporalType.DATE)
	@ColumnDefault("sysdate")
	@Temporal(TemporalType.DATE)
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
