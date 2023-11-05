package com.princess.persistence;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.princess.domain.Member;
import com.princess.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Long>, QuerydslPredicateExecutor<Product> {
	
	@Query("SELECT b FROM Board b")
	Page<Product> getBoardList(Pageable pageable);

	List<Product> findBySalesId(Member member);
	
}