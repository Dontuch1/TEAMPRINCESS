package com.princess.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.princess.domain.Board;
import com.princess.domain.CheckCondition.Type;
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
		findMember.setAgree(member.getAgree());
		System.out.println(findMember.toString());
		memberRepo.save(findMember);
	}

	// 예치금 수정
	public void updateDeposit(Member member) {
		Member findMember = memberRepo.findById(member.getId()).get();
		findMember.setDeposit(findMember.getDeposit() + member.getDeposit());
		System.out.println(findMember.toString());
		memberRepo.save(findMember);
	}

	// 내 게시글 리스트
	@Override
	public Page<Board> getBoardList(Pageable pageable, Member member) {

		return boardRepo.findByUserId(member, pageable);
	}

	// 내가 올린 상품 리스트
	@Override
	public Page<Product> getProductList(Pageable pageable, Member member) {

		return productRepo.findBySalesId(member, pageable);
	}

	// 내가 받은 후기
	@Override
	public Page<Review> getReviewList(Pageable pageable, Member member) {

		return reviewRepo.findBySeller(member, pageable);
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
	public List<Product> getBuyList(Member member) {
		List<Sales> sales = salesRepo.findByBuyer(member);
		List<Product> buyList = new ArrayList<Product>();
		for (Sales sale : sales) {
			buyList.add(productRepo.findById(sale.getPNo().getPNo()).get());
		}
		return buyList;
	}

}
