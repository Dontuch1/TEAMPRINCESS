package com.princess.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.princess.domain.Member;
import com.princess.domain.Product;
import com.princess.domain.Sales;

public interface SalesRepository extends CrudRepository<Sales, Long> {
	
	List<Sales> findByBuyer(Member member);
	
	Sales findBypNo(Product product);
}
