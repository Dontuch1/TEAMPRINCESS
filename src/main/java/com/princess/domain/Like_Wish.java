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
public class Like_Wish {
	
	//@Enumerated
	private String type;
	
	private String likeId;
	
	@Id @GeneratedValue
	private Long likeSeq;
	
	private Date regdate = new Date();
}
