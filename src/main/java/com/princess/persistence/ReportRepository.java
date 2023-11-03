package com.princess.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.princess.domain.CheckCondition.Display;
import com.princess.domain.CheckCondition.Type;
import com.princess.domain.Member;
import com.princess.domain.Report;


public interface ReportRepository extends CrudRepository <Report, Long>, QuerydslPredicateExecutor<Report>{

	Page<Report> findBySubmit(Pageable pageable, Display submit);
	
	int countByRptIdAndPostNoAndType(Member member, Long pNo, Type type);
}
