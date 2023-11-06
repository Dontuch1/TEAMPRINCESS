package com.princess.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.princess.domain.Member;
import com.princess.domain.QProduct;
import com.princess.domain.QSales;
import com.princess.domain.Sales;
import com.princess.domain.Search;
import com.princess.domain.CheckCondition.Display;
import com.princess.domain.CheckCondition.YorN;
import com.princess.persistence.SalesRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;

@Service
public class SalesServiceImpl implements SalesService {
	
	@Autowired
	private SalesRepository salesRepo;
	
	public Page<Sales> myThunderList(Search search, Pageable pageable, Member member) {
		BooleanBuilder builder = new BooleanBuilder();
		
		QProduct qProduct = QProduct.product;
		QSales qsales = QSales.sales;
		
		if (search.getSearchCondition().equals("TITLE")) {
			builder.and(qProduct.title.like("%" + search.getSearchKeyword() + "%"));
		} else if (search.getSearchCondition().equals("CONTENT")) {
			builder.and(qProduct.content.like("%" + search.getSearchKeyword() + "%"));
		} else if (search.getSearchCondition().equals("ID")) {
			builder.and(qProduct.salesId.nickName.like("%" + search.getSearchKeyword() +"%"));
		}
		builder.and(qProduct.display.eq(Display.Y));
		builder.and(qProduct.delivery.eq(YorN.Y));
		
		JPAQuery<?> subQuery = new JPAQuery<Void>()
				.from(qsales).where(qsales.thunderId.isNull().and(qsales.pNo.pNo.eq(qProduct.pNo)));
		builder.and(subQuery.exists());
		
		
		return salesRepo.findAll(builder, pageable);
	}
	
	public Sales getSales(String postNum) {
	 	return salesRepo.findByProductPNo(Long.valueOf(postNum));
	}
}
