package com.princess.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;

import com.princess.domain.CheckCondition.Role;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = "userId")
@Entity
public class Auth {
	
	@Id @GeneratedValue
	private int roleNo;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID", nullable = false, updatable = false)
	private Member userId;
	
	@ColumnDefault("MEMBER")
	private Role role;
	
	// 연관관계 설정
	
	public void setUserId(Member id) {
		this.userId = id;
		userId.getAuthList().add(this);
	}
}
