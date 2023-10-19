package com.princess.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = {"postNum", "reference", "userId"})
@Entity
public class Reply {
	
	@Id @GeneratedValue
	@Column(name = "REPLYNUM")
	private Long replyNum;
	
	@ManyToOne
	@JoinColumn(name = "BOARD_POSTNUM", nullable = false, updatable = false)
	private Board postNum;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "REPLYNUM")
	private Reply reference;
	
	private String replyContent;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID")
	private Member userId;
	
	private Date regdate = new Date();
	
	// 연관관계 설정
	
	@OneToMany(mappedBy = "reference") // Reply
	private List<Reply> referenceList = new ArrayList<Reply>();
	
	public void setUserId(Member id) { // Member
		this.userId = id;
		userId.getReplyList().add(this);
	}
	
	public void setReference(Reply replyNum) { // reference
		this.reference = replyNum;
		reference.getReferenceList().add(this);
	}
	
}
