package com.princess.service;

import com.princess.domain.Member;

public interface MemberService {

	void insertMember(Member member);

	void updateMember(Member member);

	void deleteMember(Member member);


	void getMemberList(Member member);

}