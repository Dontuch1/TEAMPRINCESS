package com.princess.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.princess.domain.Member;
import com.princess.domain.Product;
import com.princess.domain.Review;

public interface ReviewRepository extends CrudRepository<Review, Long>{
	
	Page<Review> findByReceiver(Member member, Pageable pageable);

	int countBypNoAndSenderAndReceiver(Product product, String sender, Member member);

}
