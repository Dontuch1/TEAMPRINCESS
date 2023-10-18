package com.princess.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
//@Entity
public class Product {
	
	@Id @GeneratedValue
	private Long pNo;
	
	@Enumerated()
	private String auction;
	
	@Enumerated()
	private String pCategory;
	
	private String salesId;
	
	private String title;
	
	private String content;
	
	private int price;
	
	private String upload;
	
	@Enumerated()
	private String sold;
	
	private Date AucDuration;
	
	@Enumerated()
	private String delevery;
	
	@Enumerated()
	private String display;
	
	private Date regdate = new Date();
	
	// 매핑 필요
}
