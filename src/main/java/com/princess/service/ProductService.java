package com.princess.service;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.princess.domain.Product;
import com.princess.domain.Search;

public interface ProductService {

	void insertProduct(Product prodcut, MultipartFile file);

	void updateProduct(Product product);

	void deleteProduct(Product product);

	Product getProduct(Product product);

	Page<Product> getProductList(String type, Search search);
	
//	List<Product> getProductList(Search search);

}