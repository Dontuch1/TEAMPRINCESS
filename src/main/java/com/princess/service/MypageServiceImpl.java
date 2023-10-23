package com.princess.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.princess.domain.CheckCondition.Display;
import com.princess.domain.Product;
import com.princess.domain.Search;
import com.princess.persistence.ProductRepository;


@Service
public class MypageServiceImpl{

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
		return null;
	}

}
