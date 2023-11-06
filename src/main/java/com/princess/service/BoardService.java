package com.princess.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.princess.domain.Board;
import com.princess.domain.LikeWish;
import com.princess.domain.Member;
import com.princess.domain.Product;
import com.princess.domain.Report;
import com.princess.domain.Search;
import com.princess.domain.CheckCondition.Type;

public interface BoardService {
	
	Member getMember(Member member);
	
	void updateBoard(Board board);

	void deleteBoard(Board board);

	void insertBoard(Board board, MultipartFile file);
	
	Board getBoard(Board board);

	Page<Board> getBoardList(String type, Search search, Pageable pageable);
	
	boolean isReported(Member member, Board board, Type type);

	Board getBoardId(Long boardId);

	boolean isGreated(String id, Board board, Type type);
	
	void insertGreat(LikeWish likeWish, Long greatNum, Board board1);
	
	void deleteGreat(Board board, Type type, Member member, Long greatNum);
	
	Board getGreat(Board board);
	
	void insertReport(Report report);
}