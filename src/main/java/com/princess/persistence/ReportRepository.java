package com.princess.persistence;

import org.springframework.data.repository.CrudRepository;

import com.princess.domain.Report;

public interface ReportRepository extends CrudRepository<Report, Long>{


}
