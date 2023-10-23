package com.princess.service;

import org.springframework.web.multipart.MultipartFile;

import com.princess.domain.Board;

public interface BoardService {
	
	void getBoardList(Board board); 
	
	void updateBoard(Board board);
	
	void insertBorad(Board board);

	void insertBoard(Board board, MultipartFile file);
}