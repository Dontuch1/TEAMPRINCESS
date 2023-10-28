package com.princess.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.princess.domain.Board;
import com.princess.domain.Member;
import com.princess.domain.Product;
import com.princess.domain.Review;

public interface MypageService {

	// 회원정보 가져오기
	Member getMember(Member member);

	// 회원정보 수정
	void updateMember(Member member);

	// 내 게시글 리스트
	Page<Board> getBoardList(Pageable pageable, Member member);

	// 내 등록 상품 리스트
	Page<Product> getProductList(Pageable pageable, Member member);
	
	// 내가 받은 후기
	Page<Review> getReviewList(Pageable pageable, Member member);

	// 찜 목록
	List<Product> getLikeWishList(Member member);
	
	// 예치금 넣기
	void updateDeposit(Member member);
	
	//구매 목록
	List<Product> getBuyList(Member member);




}