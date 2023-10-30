package com.princess.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.princess.domain.Board;
import com.princess.domain.Member;
import com.princess.domain.Search;

public interface BoardService {
	
	Member getMember(Member member);
	
	void updateBoard(Board board);


	void insertBoard(Board board, MultipartFile file);
	
	Board getBoard(Board board);

	Page<Board> getBoardList(String type, Search search, Pageable pageable);

	Board getBoardId(Long boardId);
}