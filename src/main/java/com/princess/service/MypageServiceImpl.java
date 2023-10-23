package com.princess.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.princess.domain.Board;
import com.princess.domain.LikeWish;
import com.princess.domain.Member;
import com.princess.domain.Review;
import com.princess.persistence.BoardRepository;
import com.princess.persistence.ProductRepository;


@Service
public class MypageServiceImpl{

	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private BoardRepository boardRepo;

	// 회원정보 가져오기
	public Member getMember(Member member) {
		return null;
	}
	// 회원정보 수정 
	public void updateMember(Member member) {
	
	}
	// 내 게시글 리스트
	public List<Board> getBoardList(Member member){
		return null;
	}
	// 내 등록 상품 리스트 >> 상품서비스임플에서 가져오기 
	// 내가 받은 후기 
	public List<Review> getReviewList(Member member){
		return null;
	}
	// 찜 목록
	public List<LikeWish> getLikeWishList(Member member){
		return null;
	}
	// 예치금 수정
	public void updateDeposit(Member member) {
	
	}


}
