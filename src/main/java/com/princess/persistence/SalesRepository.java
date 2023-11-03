package com.princess.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.princess.domain.Member;
import com.princess.domain.Sales;

public interface SalesRepository extends CrudRepository<Sales, Long> {
	
	List<Sales> findByBuyer(Member member);

	@Query("SELECT s FROM Sales s WHERE s.pNo.pNo = :productPno")
	Sales findByProductPNo(@Param("productPno") Long productPno);

}
