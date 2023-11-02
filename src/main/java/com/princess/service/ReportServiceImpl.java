package com.princess.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.princess.domain.Report;
import com.princess.domain.Search;
import com.princess.persistence.ReportRepository;
import com.querydsl.core.BooleanBuilder;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;
    
    
   
    
    @Override
    public void insertReport(Report report) {
        reportRepository.save(report);
    }

    @Override
    public void updateReport(Report report) {
        reportRepository.save(report);
    }

    @Override
    public void deleteReport(Report report) {
        reportRepository.deleteById(report.getRptNo());
    }

    @Override
    public Report getReport(Report report) {
        return reportRepository.findById(report.getRptNo()).orElse(null);
    }
    
    @Override
    public Page<Report> getReportList( Search search,Pageable pageable) {
    	BooleanBuilder builder = new BooleanBuilder();
         return reportRepository.findAll(builder,pageable);
     }
    
    public void submitReport(Report report) {
        // 배터리를 5씩 감소
        report.setBattery(report.getBattery() - 5);
        reportRepository.save(report);
    }
}