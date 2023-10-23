package com.princess.service;

import com.princess.domain.Board;

public interface BoardService {
	
	void getBoardList(Board board); 
	
	void updateBoard(Board board);
	
	void insertBorad(Board board);
}