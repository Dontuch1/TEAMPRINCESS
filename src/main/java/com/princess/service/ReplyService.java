package com.princess.service;

import java.util.List;

import com.princess.domain.Board;
import com.princess.domain.Reply;

public interface ReplyService {
	
	Reply saveReply(Reply reply);
	
	List<Reply> findByBoard(Board board);
	
	void deleteReply(Long id);
}
