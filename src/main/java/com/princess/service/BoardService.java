package com.princess.service;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.princess.domain.Board;
import com.princess.domain.Search;

public interface BoardService {
	
	Page<Board> getBoardList(Search search); 
	
	void updateBoard(Board board);
	
	void insertBorad(Board board);

	void insertBoard(Board board, MultipartFile file);
}