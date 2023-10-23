package com.princess.persistence;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.princess.domain.Report;

public interface ReportRepository extends CrudRepository<Report, String>{
	@Query
	Page<Report> getReporList(Pageable pageable);
	
	

}
