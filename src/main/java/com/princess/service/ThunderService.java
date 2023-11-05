package com.princess.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.princess.domain.Member;
import com.princess.domain.Product;
import com.princess.domain.Search;

public interface ThunderService {

	Page<Product> myThunderList(Search search, Pageable pageable, Member member);
	
	void thunderDelivery(Long productPno, Member member);
}
