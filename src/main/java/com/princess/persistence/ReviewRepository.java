package com.princess.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.princess.domain.Member;
import com.princess.domain.Review;

public interface ReviewRepository extends CrudRepository<Review, Long>{
	
	Page<Review> findBySeller (Member member, Pageable pageable);

}
