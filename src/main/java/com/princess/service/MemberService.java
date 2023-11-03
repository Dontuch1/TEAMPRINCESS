package com.princess.service;

import java.util.List;

import com.princess.domain.Member;

public interface MemberService {

	void insertMember(Member member);

	void updateMember(Member member);

	void deleteMember(Member member);

	List<Member> getMemberList(Member member);
	
	Member getMember(Member meber);

	void deleteThunder(Member member);
	
	void updateThunder(Member member);

}