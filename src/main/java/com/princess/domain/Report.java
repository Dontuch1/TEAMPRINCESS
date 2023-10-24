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

import com.princess.domain.CheckCondition.Type;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = "rptId")
@Entity
public class Report {
	
	@Id @GeneratedValue
	private Long rptNo;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID", nullable = false, updatable = false)
	private Member rptId;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Type type;

	private Long postNo;
	
	private String rptCon;
	
	@ColumnDefault("sysdate")
	@Temporal(TemporalType.DATE)
	private Date rptDate = new Date();
	
	// 연관관계 설정
	
	public void setRptId(Member id) { // Member
		this.rptId = id;
		rptId.getReportList().add(this);
	}
}
