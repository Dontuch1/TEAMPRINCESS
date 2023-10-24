package com.princess.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.princess.domain.Member;
import com.princess.persistence.MemberRepository;

@Controller
public class MemberServiceImpl implements MemberSerivce {
	
	@Autowired
	MemberRepository memberRepo;
	
	public void insertMember(Member member) {
		memberRepo.save(member);
	}
}
