package com.princess.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.princess.domain.Member;
import com.princess.persistence.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepo;
	

	@Autowired
	private PasswordEncoder encoder;

	public void insertMember(Member member) {
		member.setPassword(encoder.encode(member.getPassword()));
		memberRepo.save(member);
	}

	public void updateMember(Member member) {

	}

	public void deleteMember(Member member) {

	}

	public void getMemberList(Member member) {

	}

}
