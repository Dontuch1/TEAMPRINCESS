package com.princess.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
import com.princess.domain.CheckCondition.Type;
import com.princess.domain.CheckCondition.YorN;
import com.princess.domain.LikeWish;
import com.princess.domain.Member;
import com.princess.domain.Product;
import com.princess.domain.QProduct;
import com.princess.domain.Report;
import com.princess.domain.Sales;
import com.princess.domain.Search;
import com.princess.persistence.AuctionRepository;
import com.princess.persistence.LikeWishRepository;
import com.princess.persistence.MemberRepository;
import com.princess.persistence.ProductRepository;
import com.princess.persistence.ReportRepository;
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

	@Autowired
	private MemberRepository memberRepo;

	@Autowired
	private LikeWishRepository likewishRepo;

	@Autowired
	private ReportRepository reportRepo;

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
		findProduct.setDelivery(product.getDelivery());
		findProduct.setSold(product.getSold());
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
		} else if (search.getSearchCondition().equals("NICKNAME")) {
			builder.and(qProduct.salesId.nickName.like("%" + search.getSearchKeyword() + "%"));
		}

		builder.and(qProduct.display.eq(Display.Y));

		// Pageable pageable = PageRequest.of(0, 4, Sort.Direction.DESC, "pNo");

		return productRepo.findAll(builder, pageable);
	}

	public int countAuctions(Product product) {
		return productRepo.countBypNo(product.getPNo());
	}

	public Auction getAuctionMaxPrice(Product product) {
		if (auctionRepo.findBypNo(product.getPNo()).isEmpty()) {
			return null;
		} else
			return auctionRepo.findBypNo(product.getPNo()).get(0);
	}

	public List<Auction> getAuctionList(Product product) {
		return auctionRepo.findBypNo(product.getPNo());
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

	public boolean isAuctioned(Product product, String id) {
		if (auctionRepo.countByPNoAndId(product.getPNo(), id) == 3) {
			return true;
		} else
			return false;
	}

	public void insertAuction(Product product, String id, int bid) {
		Auction auction = new Auction();
		auction.setPNo(product);
		Member member = new Member();
		member.setId(id);
		auction.setAuctionId(member);
		auction.setAuctionPrice(bid);
		auctionRepo.save(auction);
	}

	public void setMemberDepoist(Member member) {
		Member findMember = memberRepo.findById(member.getId()).get();
		findMember.setDeposit(member.getDeposit());
		memberRepo.save(findMember);
	}

	public Member getMember(Member member) {
		return memberRepo.findById(member.getId()).get();
	}

	public List<Auction> getBidList(Member member, Product product) {
		List<Auction> list = new ArrayList<Auction>();
		if (auctionRepo.findByAuctionIdOrderByAuctionPriceDesc(member).isEmpty()) {
			Auction auction = new Auction();
			auction.setAuctionPrice(0);
			list.add(auction);
		} else {
			for (Auction auc : auctionRepo.findByAuctionIdOrderByAuctionPriceDesc(member)) {
				if (auc.getPNo().getPNo() == product.getPNo()) {
					list.add(auc);
				}
			}
		}
		return list;
	}

	public void editProduct(Product product, MultipartFile file) {
		Product findProduct = productRepo.findById(product.getPNo()).get();

		if (!file.isEmpty()) {
			String filename = UUID.randomUUID().toString() + file.getOriginalFilename();
			try {
				file.transferTo(new File(path + filename));
				findProduct.setUpload("/upload/" + filename);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}

		findProduct.setPCategory(product.getPCategory());
		findProduct.setTitle(product.getTitle());
		findProduct.setContent(product.getContent());
		findProduct.setPrice(product.getPrice());
		findProduct.setDelivery(product.getDelivery());
		productRepo.save(findProduct);
	}

	public List<LikeWish> getWishList(Product product, Type type) {
		return likewishRepo.findBypNoAndType(product.getPNo(), type);
	}

	public boolean isWished(String id, Product product, Type type) {
		boolean isWished = false;
		for (LikeWish like : likewishRepo.findBypNoAndType(product.getPNo(), type)) {
			if (like.getLikeId().getId().equals(id)) {
				isWished = true;
				break;
			}
		}
		return isWished;
	}

	public void insertLike(LikeWish likeWish) {
		System.out.println("라이크 인서트");
		likewishRepo.save(likeWish);
	}

	public void deleteLike(Product product, Type type, Member member) {
		LikeWish findWish = likewishRepo.findBypNoAndTypeAndLikeId(product.getPNo(), type, member);
		System.out.println("findWish : " + findWish);
		likewishRepo.delete(findWish);
	}

	public int countWishes(Product product, Type type) {
		return likewishRepo.countBypNoAndType(product.getPNo(), type);
	}

	public boolean isReported(Member member, Product product, Type type) {
		int result = reportRepo.countByRptIdAndPostNoAndType(member, product.getPNo(), Type.PRODUCT);
		if (result == 1)
			return true;
		else
			return false;
	}

	public void insertReport(Report report) {
		Report findReport = reportRepo.findByPostNoAndType(report.getPostNo(), Type.PRODUCT);
		if (findReport != null) {
			findReport.setRptCon(
					findReport.getRptCon() + "\n" + "(" + report.getRptId().getId() + ") " + report.getRptCon());
			reportRepo.save(findReport);
		} else
			reportRepo.save(report);
	}

	public boolean isAuctionSold(Product product) {
		if (saleseRepo.findBypNo(product) == null) {
			return false;
		} else
			return true;
	}

	public void buyAuction(Product product) {
		Sales sales = new Sales();
		Auction auction = auctionRepo.findBypNo(product.getPNo()).get(0);
		sales.setBuyer(auction.getAuctionId());
		sales.setPNo(product);
		saleseRepo.save(sales);
	}
}
