package com.princess.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.princess.domain.Member;
import com.princess.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Long>, QuerydslPredicateExecutor<Product> {

	Page<Product> findBySalesId(Member member, Pageable pageable);

}