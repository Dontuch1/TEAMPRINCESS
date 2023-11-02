package com.princess.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.princess.domain.Report;

public interface ReportRepository extends CrudRepository <Report, Long>, QuerydslPredicateExecutor<Report>{

	@Query("SELECT b FROM Report b")
	Page<Report> getReportList(Pageable pageable);

}
