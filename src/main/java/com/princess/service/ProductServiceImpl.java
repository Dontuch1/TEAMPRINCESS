package com.princess.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.princess.domain.Auction;
import com.princess.domain.CheckCondition.Display;
import com.princess.domain.CheckCondition.YorN;
import com.princess.domain.Member;
import com.princess.domain.Product;
import com.princess.domain.QProduct;
import com.princess.domain.Sales;
import com.princess.domain.Search;
import com.princess.persistence.AuctionRepository;
import com.princess.persistence.ProductRepository;
import com.princess.persistence.SalesRepository;
import com.querydsl.core.BooleanBuilder;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private AuctionRepository auctionRepo;

	@Autowired
	private SalesRepository saleseRepo;

	@Value("${file.direc}")
	private String path;

	public void insertProduct(Product product, MultipartFile file) {
		if (!file.isEmpty()) {
			String filename = UUID.randomUUID().toString() + file.getOriginalFilename();
			try {
				file.transferTo(new File(path + filename));
				product.setUpload("/upload/" + filename);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("인서트 되는 : " + product.toString());
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
		productRepo.save(findProduct);
	}

	public Product getProduct(Product product) {
		return productRepo.findById(product.getPNo()).get();
	}

	public Page<Product> getProductList(String type, Search search, Pageable pageable) {
		BooleanBuilder builder = new BooleanBuilder();

		QProduct qProduct = QProduct.product;

		if (type.equals("prod")) {
			builder.and(qProduct.auction.eq(YorN.N));
		} else
			builder.and(qProduct.auction.eq(YorN.Y));

		if (search.getSearchCondition().equals("TITLE")) {
			builder.and(qProduct.title.like("%" + search.getSearchKeyword() + "%"));
		} else if (search.getSearchCondition().equals("CONTENT")) {
			builder.and(qProduct.content.like("%" + search.getSearchKeyword() + "%"));
		} else if (search.getSearchCondition().equals("ID")) {
			builder.and(qProduct.salesId.nickName.like("%" + search.getSearchKeyword() + "%"));
		}

		builder.and(qProduct.display.eq(Display.Y));

		// Pageable pageable = PageRequest.of(0, 4, Sort.Direction.DESC, "pNo");

		return productRepo.findAll(builder, pageable);
	}
	
	
	
	// THUNDER Controller
	@Override
	public Page<Product> myThunderList(Search search, Pageable pageable) {
		BooleanBuilder builder = new BooleanBuilder();
		
		QProduct qProduct = QProduct.product;
		if (search.getSearchCondition().equals("TITLE")) {
			builder.and(qProduct.title.like("%" + search.getSearchKeyword() + "%"));
		} else if (search.getSearchCondition().equals("CONTENT")) {
			builder.and(qProduct.content.like("%" + search.getSearchKeyword() + "%"));
		} else if (search.getSearchCondition().equals("ID")) {
			builder.and(qProduct.salesId.nickName.like("%" + search.getSearchKeyword() +"%"));
		}
		builder.and(qProduct.display.eq(Display.Y));
		builder.and(qProduct.delevery.eq(YorN.Y));
		
		return productRepo.findAll(builder, pageable);
	}

	

	public Auction getAuctionMaxPrice(Product product) {
		if (auctionRepo.findBypNoOrderByAuctionNoDesc(product).isEmpty()) {
			System.out.println("empty");
			return null;
		} else
			System.out.println("nnot empty");
			return auctionRepo.findBypNoOrderByAuctionNoDesc(product).get(0);
	}

	public List<Auction> getAuctionList(Product product) {
		return null;
	}

	public void buyProduct(Product product, String buyer) {
		Sales newSales = new Sales();
		Member member = new Member();
		member.setId(buyer);
		product.setSold(YorN.Y);
		newSales.setBuyer(member);
		newSales.setPNo(product);
		saleseRepo.save(newSales);
		productRepo.save(product);
	}

}
