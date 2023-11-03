package com.princess.persistence;

import org.springframework.data.repository.CrudRepository;

import com.princess.domain.CheckCondition.Type;
import com.princess.domain.Member;
import com.princess.domain.Report;

public interface ReportRepository extends CrudRepository<Report, Long>{

	int countByRptIdAndPostNoAndType(Member member, Long pNo, Type type);
}
