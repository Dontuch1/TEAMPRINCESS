package com.princess.persistence;

import org.springframework.data.repository.CrudRepository;

import com.princess.domain.Member;


public interface MemberRepository extends CrudRepository<Member, String>{
	
}