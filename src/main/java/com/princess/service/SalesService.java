package com.princess.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.princess.domain.Member;
import com.princess.domain.Sales;
import com.princess.domain.Search;

public interface SalesService {

	Page<Sales> myThunderList(Search search, Pageable pageable, Member member);

	Sales getSales(String postNum);

}
