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
import org.hibernate.annotations.DynamicInsert;

import com.princess.domain.CheckCondition.Display;
import com.princess.domain.CheckCondition.Type;

import lombok.Data;
import lombok.ToString;

@DynamicInsert
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
	
	@Enumerated(EnumType.STRING)
	@ColumnDefault("'H'")
	private Display submit;
	
	@Temporal(TemporalType.DATE)
	@ColumnDefault("SYSDATE")
	private Date regdate = new Date();
	
	// 연관관계 설정
	
	public void setRptId(Member id) { // Member
		this.rptId = id;
		rptId.getReportList().add(this);
	}
}
