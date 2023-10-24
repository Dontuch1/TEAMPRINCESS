package com.princess.service;

import org.springframework.data.domain.Page;

import com.princess.domain.Report;
import com.princess.domain.Search;

public interface ReportService {

	void insertReport(Report report);

	void updateReport(Report report);

	void deleteReport(Report report);

	Report getReport(Report report);

	Page<Report> getReportList(Search search);

}