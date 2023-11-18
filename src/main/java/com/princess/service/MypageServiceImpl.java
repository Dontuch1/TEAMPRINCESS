package com.princess.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.princess.domain.Board;
import com.princess.domain.CheckCondition.Display;
import com.princess.domain.CheckCondition.Type;
import com.princess.domain.CheckCondition.YorN;
import com.princess.domain.LikeWish;
import com.princess.domain.Member;
import com.princess.domain.Product;
import com.princess.domain.Review;
import com.princess.domain.Sales;
import com.princess.persistence.BoardRepository;
import com.princess.persistence.LikeWishRepository;
import com.princess.persistence.MemberRepository;
import com.princess.persistence.ProductRepository;
import com.princess.persistence.ReviewRepository;
import com.princess.persistence.SalesRepository;

@Service
public class MypageServiceImpl implements MypageService {

	@Autowired
	private MemberRepository memberRepo;
	@Autowired
	private BoardRepository boardRepo;
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private LikeWishRepository likeWishRepo;
	@Autowired
	private ReviewRepository reviewRepo;
	@Autowired
	private SalesRepository salesRepo;

	// 회원정보 가져오기
	public Member getMember(Member member) {
		return memberRepo.findById(member.getId()).get();
	}

	// 회원정보 수정
	@Override
	public void updateMember(Member member) {
		Member findMember = memberRepo.findById(member.getId()).get();
		findMember.setNickName(member.getNickName());
		findMember.setDeposit(member.getDeposit());
		findMember.setEmail(member.getEmail());
		findMember.setPhone(member.getPhone());
		if (member.getAgree() == null) {
			findMember.setAgree(YorN.N);
		} else {
			findMember.setAgree(member.getAgree());
		}
		findMember.setBirth(member.getBirth());

		memberRepo.save(findMember);
	}

	// 예치금 수정
	public void updateDeposit(Member member) {
		Member findMember = memberRepo.findById(member.getId()).get();
		findMember.setDeposit(findMember.getDeposit() + member.getDeposit());

		memberRepo.save(findMember);
	}

	// 내 게시글 리스트
	@Override
	public Page<Board> getBoardList(Pageable pageable, Member member) {

		return boardRepo.findByUserIdAndDisplay(member, pageable, Display.Y);
	}

	// 내가 올린 상품 리스트
	@Override
	public List<Product> getProductList(Member member) {

		return productRepo.findBySalesId(member);
	}

	// 내가 받은 후기
	@Override
	public Page<Review> getReviewList(Pageable pageable, Member member) {

		return reviewRepo.findByReceiver(member, pageable);
	}

	// 내가 보낸 후기
	@Override
	public Page<Review> getSentReviewList(Pageable pageable, String sender) {

		return reviewRepo.findBySender(sender, pageable);
	}

	// 찜 목록
	@Override
	public List<Product> getLikeWishList(Member member) {
		List<LikeWish> likeWish = likeWishRepo.findByLikeIdAndType(member, Type.PRODUCT);
		List<Product> likeProd = new ArrayList<Product>();
		for (LikeWish like : likeWish) {
			likeProd.add(productRepo.findById(like.getPNo()).get());
		}
		return likeProd;
	}

	// 구매 목록
	@Override
	public Map<Product, String[]> getBuyList(Member member) {
		List<Sales> sales = salesRepo.findByBuyer(member);

		Map<Product, String[]> buyList = new LinkedHashMap<Product, String[]>();

		Member receiver = new Member();

		for (Sales sale : sales) {
			int thunder = 1;
			String thun = "";
			String tra = "";

			if (sale.getPNo().getDelivery().equals(YorN.Y)) {// 썬더맨 이용 상품
				if (sale.getThunderId() != null) {// 배치가 됐을때
					receiver.setId(sale.getThunderId());
					thunder = reviewRepo.countBypNoAndSenderAndReceiver(sale.getPNo(), member.getId(), receiver);

					if (thunder == 0) { // 썬더맨 이용상품 + 썬더맨 후기를 작성안했을때
						thun = sale.getThunderId();
					} else {// 썬더맨 이용상품 + 썬더맨 후기를 작성했을때
						thun = "";
					}
				} else {// 배치 안됐을때
					thun = "아직 썬더맨이 없어요 ㅋㅋㅎㅎㅠㅠ;;";
				}
			} // 썬더맨 이용상품이 아닐때
			int trade = reviewRepo.countBypNoAndSenderAndReceiver(sale.getPNo(), member.getId(),
					sale.getPNo().getSalesId());
			if (trade == 0)
				tra = sale.getPNo().getSalesId().getId();
			else
				tra = "";

			String[] str = { thun, tra };
			buyList.put(productRepo.findById(sale.getPNo().getPNo()).get(), str);
		}
		return buyList;
	}

	// 리뷰 등록
	@Override
	public void insertReview(Review review, Product product) {
		reviewRepo.save(review);
	}

	// 배터리 수정
	public void updateBattery(Member member) {
		memberRepo.save(member);

	}

	// 썬더 아이디
	public String thunderId(Product product) {
		Sales sales = salesRepo.findBypNo(product);
		if (sales != null && sales.getThunderId() != null) {
			return sales.getThunderId();
		} else {
			return "";
		}

	}
	// 구매자 아이디
	public String buyerId(Product product) {
		Sales sales = salesRepo.findBypNo(product);
		if (sales == null) {

			return null;
		}
		return sales.getBuyer().getId();
	}
	// 후기 작성여부
	public boolean isReviewed(Product product, String sender, String receiver) {
		Member member= new Member();
		member.setId(receiver);
		int cnt = reviewRepo.countBypNoAndSenderAndReceiver(product, sender, member);
		if(cnt==0) return false;
		else return true;
	}
	
	// 캐삭
	public void	disalbeMember(Member member) {
		member = memberRepo.findById(member.getId()).get();
		member.setEnabled(Display.N);
		memberRepo.save(member);
	}

}
