package com.princess.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.princess.domain.Product;
import com.princess.domain.Search;
import com.princess.domain.CheckCondition.Display;
import com.princess.persistence.ProductRepository;
import com.querydsl.core.BooleanBuilder;


@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;

	public void insertProduct(Product product) {
		productRepo.save(product);
	}

	public void updateProduct(Product product) {
		Product findProduct = productRepo.findById(product.getPNo()).get();
		
		findProduct.setPCategory(product.getPCategory());
		findProduct.setTitle(product.getTitle());
		findProduct.setContent(product.getContent());
		findProduct.setPrice(product.getPrice());
		findProduct.setUpload(product.getUpload());
		findProduct.setDelevery(product.getDelevery());
		productRepo.save(findProduct);
	}

	public void deleteProduct(Product product) {
		Product findProduct = productRepo.findById(product.getPNo()).get();
		
		findProduct.setDisplay(Display.N);
	}

	public Product getProduct(Product product) {
		return productRepo.findById(product.getPNo()).get();
	}

	public Page<Product> getProductList(Search search) {
		BooleanBuilder builder = new BooleanBuilder();
		
		QProduct qProduct = QProduct.product;
		
		if (search.getSearchCondition().equals("TITLE")) {
			builder.and(qProduct.title.like("%" + search.getSearchKeyword() + "%"));
		} else if (search.getSearchCondition().equals("CONTENT")) {
			builder.and(qProduct.content.like("%" + search.getSearchKeyword() + "%"));
		}
		
		Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "seq");
		
		return null; //productRepo.findAll(builder, pageable);;
	}

}
