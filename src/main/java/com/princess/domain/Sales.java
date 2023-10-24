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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;

import com.princess.domain.CheckCondition.YorN;

import lombok.Data;
import lombok.ToString;

@Data
@ToString (exclude = {"salesId", "salesNo", "reviewList"})
@Entity
public class Sales {
	
	@Id @GeneratedValue
	private Long traNo;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID", nullable = false, updatable = false)
	private Member salesId;
	
	@ManyToOne
	@JoinColumn(name = "PRODUCT_PNO", nullable = false, updatable = false)
	private Product salesNo;
	
	private String thunderId;
	

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private YorN cmt;
	
	@ColumnDefault("sysdate")
	@Temporal(TemporalType.DATE)
	private Date salesRegdate = new Date();
	
	// 연관관계 설정
	
	@OneToMany (mappedBy = "buyId") // Review
	private List<Review> reviewList = new ArrayList<Review>();
	
	public void setSalesId(Member id) { // Member
		this.salesId = id;
		salesId.getSalesList().add(this);
	}
	
	public void setSalesNo(Product pNo) { // Product
		this.salesNo = pNo;
		salesNo.getSalesList().add(this);
	}
}