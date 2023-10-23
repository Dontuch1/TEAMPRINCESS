package com.princess.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.princess.domain.Board;
import com.princess.domain.Member;
import com.princess.domain.Product;
import com.princess.domain.Review;
import com.princess.persistence.MemberRepository;

@Service
public class MypageServiceImpl implements MypageService {


//	@Autowired
//	private BoardRepository boardRepo;

	@Autowired
	private MemberRepository memberRepo;
	
	// 회원정보 가져오기
	@Override
	public Member getMember(Member member) {
		return memberRepo.findById(member.getId()).get();
	}

	// 회원정보 수정 //예치금 수정
	@Override
	public void updateMember(Member member) {
		Member findMember = memberRepo.findById(member.getId()).get();
		findMember.setNickName(member.getNickName());
		findMember.setDeposit(member.getDeposit());
		findMember.setEmail(member.getEmail());
		findMember.setPhone(member.getPhone());
		
		memberRepo.save(findMember);
	}

	// 내 게시글 리스트
	@Override
	public List<Board> getBoardList(Member member) {
		return null;
	}

	// 내 등록 상품 리스트 >> 상품서비스임플에서 가져오기
	// 내가 받은 후기
	@Override
	public List<Review> getReviewList(Member member) {

		return null;
	}

	// 찜 목록
	@Override
	public List<Product> getLikeWishList(Member member) {

	// BooleanBuilder builder = new BooleanBuilder();
//
//		QLikeWish qLikeWish = QLikeWish.likeWish;
//
//		Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "pNo");
//		
		member.getLikeWishList();
		return null;

	}


}
