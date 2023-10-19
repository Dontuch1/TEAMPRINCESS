package com.princess.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Board {
	
	@Id @GeneratedValue
	private Long postNum;
	
	//@Enumerated()
	private String cmCategory;
	
	private String userid;
	
	private String title;
	
	private String content;
	
	private String photoPath;
	
	private Long great;
	
	//@Enumerated()
	private String display;
	
	private Date regdate = new Date();
}
