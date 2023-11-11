package com.princess.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.princess.domain.Member;
import com.princess.domain.Review;
import com.princess.domain.Sales;
import com.princess.domain.Search;

public interface ThunderService {

	void thunderDelivery(Long productPno, Member member);
	
	Page<Sales> myThunderList(Search search, Pageable pageable);

	List<Review> getreviewList();
	
	void deleteThunder(Member member);
	
	void updateThunder(Member member);
	
}
