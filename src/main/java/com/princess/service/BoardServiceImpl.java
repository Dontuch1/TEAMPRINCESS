package com.princess.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.princess.domain.Board;
import com.princess.domain.LikeWish;
import com.princess.domain.CheckCondition.CmCategory;
import com.princess.domain.CheckCondition.Display;
import com.princess.domain.CheckCondition.Type;
import com.princess.domain.Member;
import com.princess.domain.Product;
import com.princess.domain.QBoard;
import com.princess.domain.Sales;
import com.princess.domain.Search;
import com.princess.persistence.BoardRepository;
import com.princess.persistence.LikeWishRepository;
import com.princess.persistence.MemberRepository;
import com.princess.persistence.ReportRepository;
import com.querydsl.core.BooleanBuilder;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository boardRepo;
	@Autowired
	private MemberRepository memberRepo;
	@Autowired
	private ReportRepository reportRepo;
	@Autowired
	private LikeWishRepository likewishRepo;
	
	@Value("${file.direc}")
	private String path;
	
	// 회원정보 가져오기
	public Member getMember(Member member) {
		return memberRepo.findById(member.getId()).get();
	}
	
	
	public Board getBoard(Board board) {
		return boardRepo.findById(board.getPostNum()).get();
	}
	
	public Board getBoardId(Long boardId) {
		return boardRepo.findById(boardId).get();
	}
	
	public void insertBoard(Board board, MultipartFile file) {
		if (!file.isEmpty()) {
			String filename = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();	
			try {
				file.transferTo(new File(path + filename));
				board.setPhotoPath("/upload/" + filename); 
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			} 
		}
		boardRepo.save(board);
	}
	
	@Override
	public Page<Board> getBoardList(String type, Search search, Pageable pageable) {
		BooleanBuilder builder = new BooleanBuilder();
		
		QBoard qboard = QBoard.board;
		
		if (type.equals("notice")) { builder.and(qboard.cmCategory.eq(CmCategory.NOTICE)); } 
		else if (type.equals("lost")) { builder.and(qboard.cmCategory.eq(CmCategory.LOST)); } 
		else if (type.equals("food")) { builder.and(qboard.cmCategory.eq(CmCategory.FOOD)); } 
		else if (type.equals("tmi")) { builder.and(qboard.cmCategory.eq(CmCategory.TMI)); } 
		else if (type.equals("qna")) { builder.and(qboard.cmCategory.eq(CmCategory.QNA)); } 
		else if (type.equals("meet")) { builder.and(qboard.cmCategory.eq(CmCategory.MEET)); } 
		
		if (search.getSearchCondition().equals("TITLE")) {
			builder.and(qboard.title.like("%" + search.getSearchKeyword() + "%"));
		} else if (search.getSearchCondition().equals("CONTENT")) {
			builder.and(qboard.content.like("%" + search.getSearchKeyword() + "%"));
		} else if (search.getSearchCondition().equals("NICKNAME")) {
			builder.and(qboard.userId.nickName.like("%" + search.getSearchKeyword() + "%"));
		}
		
		builder.and(qboard.display.eq(Display.Y));
		
		return boardRepo.findAll(builder, pageable);
	}
	
	

	public void updateBoard(Board board) {
		
	}

	public void deleteBoard(Board board) {
		Board findboard = boardRepo.findById(board.getPostNum()).get();
		System.out.println("findboard : "+findboard.toString());
		findboard.setDisplay(Display.N);
		boardRepo.save(findboard);
	}
	
	public boolean isReported(Member member, Board board, Type type) {
		Product product = new Product();
		product.setPNo(board.getPostNum());
		System.out.println(product.getPNo());
		int result = reportRepo.countByRptIdAndPostNoAndType(member, product.getPNo(), Type.COMMUNITY);
		if (result == 1)
			return true;
		else
			return false;
	}
	
	public boolean isGreated(String id, Board board, Type type) {
		boolean isGreated = false;
		for(LikeWish like : likewishRepo.findBypNoAndType(board.getPostNum(), type)) {
			if(like.getLikeId().getId().equals(id)) {
				isGreated = true;
				break;
			}
		}
		return isGreated;
	}
	


}