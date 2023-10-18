package com.princess.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Enumerated;

import lombok.Data;

@Data
//@Entity
public class Sales {

	private String salesId;
	
	private Long salesNo;
	
	private String thunderId;
	
	@Enumerated()
	private String salesType;
	
	@Enumerated()
	private String comment;
	
	private Date salesRegdate = new Date();
	
	// 매핑 필요
}
