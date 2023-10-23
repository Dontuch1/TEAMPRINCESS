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

import org.hibernate.annotations.ColumnDefault;

import com.princess.domain.CheckCondition.Type;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = "likeId")
@Entity
public class LikeWish {
	
	@Id @GeneratedValue
	private Long likeSeq;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Type type;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID", nullable = false, updatable = false)
	private Member likeId;
	
	@ColumnDefault("sysdate")
	private Date regdate = new Date();
	
	// 연관관계 설정
	
	public void setLikeId(Member id) { // Member
		this.likeId = id;
		likeId.getLikeWishList().add(this);
	}
}
