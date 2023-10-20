package com.princess.service;

import org.springframework.data.domain.Page;

import com.princess.domain.Product;
import com.princess.domain.Search;

public interface ProductService {

	void insertProduct(Product prodcut);

	void updateProduct(Product product);

	void deleteProduct(Product product);

	Product getProduct(Product product);

	Page<Product> getProductList(Search search);

}