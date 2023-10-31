package com.princess.persistence;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.princess.domain.Member;
import com.princess.domain.Product;


public interface MemberRepository extends CrudRepository<Member, String>{
	
}
