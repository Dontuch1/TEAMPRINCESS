package com.princess.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.princess.domain.CheckCondition.Display;
import com.princess.domain.Member;
import com.princess.domain.Report;

public interface ReportService {

	void insertReport(Report report);

	void updateReport(Report report);

	void deleteReport(Report report);

	Page<Report> getReportList(Pageable pageable, Display submit);

	void changeReportStatus(Long rptNo, String type);

	Report getReport(Report report);

}