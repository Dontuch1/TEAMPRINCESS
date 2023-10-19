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
public class Report {
	
	@Id @GeneratedValue
	private Long rptNo;
	
	private String rptId;
	
	@Enumerated
	private String type;

	private Long postNo;
	
	private String rptCon;
	
	private Date rptDate = new Date();
}
