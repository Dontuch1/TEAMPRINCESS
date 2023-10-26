package com.princess.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.princess.domain.Report;
import com.princess.domain.Search;
import com.princess.persistence.ReportRepository;

@Service
public class ReportServiceImpl implements ReportService{
	
	@Autowired
	private ReportRepository reportRepo;
	
	public void insertReport(Report report) {
		reportRepo.save(report);
	}
	public void updateReport(Report report) {
		Report findReport = reportRepo.findById(report.getRptNo()).get();
		
		findReport.setRptCon(report.getRptCon());
		findReport.setPostNo(report.getRptNo());
		findReport.setRptNo(report.getRptNo());
		reportRepo.save(findReport);
	}
	public void deleteReport(Report report) {
		reportRepo.deleteById(report.getRptNo());
	}
	public Report getReport(Report report) {
		return reportRepo.findById(report.getRptNo()).get();
	}
	public Page<Report> getReportList(Search search) {
		//Pageable pageable = PageRequest.of(0, 10,Sort.Direction.DESC,"rptNo");
		return null;
		
	}

}
