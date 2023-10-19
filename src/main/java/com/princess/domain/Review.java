package com.princess.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Enumerated;

import lombok.Data;

@Data
//@Entity
public class Review {
	
	private String reviewId;
	
	private String buyId;
	
	private Long reviewNo;
	
	//@Enumerated()
	private String reviewType;
	
	private String content;
	
	private Date reviewRegdate = new Date();
	
	// 매핑 필요
}
