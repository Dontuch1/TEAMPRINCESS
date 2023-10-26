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

import com.princess.domain.CheckCondition.Rating;

import lombok.Data;
import lombok.ToString;;

@Data
@ToString(exclude = { "seller", "pNo" })
@Entity
public class Review {

	@Id
	@GeneratedValue
	private Long reviewNo;

	@Column(nullable = false)
	private String buyer;

	@ManyToOne
	@JoinColumn(name = "MEMBER_ID", nullable = false, updatable = false)
	private Member seller;

	@ManyToOne
	@JoinColumn(name = "PRODUCT_PNO", nullable = false, updatable = false)
	private Product pNo;

	@Column(nullable = false)
	private String content;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	@ColumnDefault("UP")
	private Rating review;

	@Temporal(TemporalType.DATE)
	@ColumnDefault("SYSDATE")
	private Date regdate = new Date();

	// 연관관계 설정
	public void getSellerId(Member id) { // Member
		this.seller = id;
		seller.getReviewList().add(this);
	}

	public void getPNo(Product pNo) { // Product
		this.pNo = pNo;
		pNo.getReviewList().add(this);
	}

}
