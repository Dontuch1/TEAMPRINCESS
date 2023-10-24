package com.princess.service;

import java.util.List;

import com.princess.domain.Board;
import com.princess.domain.Member;
import com.princess.domain.Product;
import com.princess.domain.Review;

public interface MypageService {

	


	// 회원정보 수정
	void updateMember(Member member);

	// 내 게시글 리스트
	List<Board> getBoardList(Member member);

	// 내 등록 상품 리스트 >> 상품서비스임플에서 가져오기
	// 내가 받은 후기
	List<Review> getReviewList(Member member);

	// 찜 목록
	List<Product> getLikeWishList(Member member);



}