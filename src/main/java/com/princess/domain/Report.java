package com.princess.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.princess.domain.CheckCondition.Type;

import lombok.Data;
import lombok.ToString;

<<<<<<< HEAD
@Data
@ToString(exclude = "rptId")
@Entity
=======
@Getter
@Setter
@ToString
//@Entity
>>>>>>> branch 'main' of https://github.com/Dontuch1/TeamPrincess.git
public class Report {
	
	@Id @GeneratedValue
	private Long rptNo;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID", nullable = false, updatable = false)
	private Member rptId;
	
	@Enumerated(EnumType.STRING)
	private Type type;

	private Long postNo;
	
	private String rptCon;
	
	private Date rptDate = new Date();
	
	// 연관관계 설정
	
	public void setRptId(Member id) { // Member
		this.rptId = id;
		rptId.getReportList().add(this);
	}
}
