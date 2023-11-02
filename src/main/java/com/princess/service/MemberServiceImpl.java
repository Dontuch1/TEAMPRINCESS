package com.princess.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.princess.domain.Member;
import com.princess.domain.CheckCondition.Role;
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
		memberRepo.delete(member);
	}
	// 회원리스트 가져오기
	public  List<Member> getMemberList(Member member) {
		return  (List<Member>) memberRepo.findAll();
	}
	
	public Member getMember(Member member) {
		return memberRepo.findById(member.getId()).get();
	}
	
	public void deleteThunder(Member member) {
		Member findmember = memberRepo.findById(member.getId()).get();
		System.out.println("findmember : "+findmember.toString());
		findmember.setAuth(Role.MEMBER);
		memberRepo.save(findmember);
	}
	
	public void updateThunder(Member member) {
		Member findmember = memberRepo.findById(member.getId()).get();
		System.out.println("findmember : "+findmember.toString());
		findmember.setAuth(Role.THUNDER);
		memberRepo.save(findmember);
	}
}
