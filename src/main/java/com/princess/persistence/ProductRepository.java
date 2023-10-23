package com.princess.persistence;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.princess.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Long>, QuerydslPredicateExecutor<Product> {

}