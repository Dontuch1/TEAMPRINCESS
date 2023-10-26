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

import com.princess.domain.CheckCondition.YorN;

import lombok.Data;
import lombok.ToString;

@Data
@ToString (exclude = {"buyer", "pNo"})
@Entity
public class Sales {
	
	@Id @GeneratedValue
	private Long traNo;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID", nullable = false, updatable = false)
	private Member buyer;
	
	@ManyToOne
	@JoinColumn(name = "PRODUCT_PNO", nullable = false, updatable = false)
	private Product pNo;
	
	private String thunderId;
	

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private YorN rated;
	
	@Temporal(TemporalType.DATE)
	@ColumnDefault("SYSDATE")
	private Date regdate = new Date();
	
	// 연관관계 설정
	
	public void setSalesId(Member id) { // Member
		this.buyer = id;
		buyer.getSalesList().add(this);
	}
	
	public void setSalesNo(Product pNo) { // Product
		this.pNo = pNo;
		pNo.getSalesList().add(this);
	}
}