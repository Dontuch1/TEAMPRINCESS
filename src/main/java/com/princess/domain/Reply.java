package com.princess.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Reply {
	
	@Id @GeneratedValue
	private Long replyNum;
	
	private Long postNum;
	
	private String reference;
	
	private String replyContent;
	
	private String userid;
	
	private Date regdate = new Date();
}
