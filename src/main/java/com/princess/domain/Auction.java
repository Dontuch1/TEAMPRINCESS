package com.princess.domain;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Data;

@Data
//@Entity
public class Auction {
	
	private String auctionId;
	
	private Long auctionNo;
	
	private int auctionPrice;
	
	private Date regdate = new Date();
	
	// 매핑 필요
}
