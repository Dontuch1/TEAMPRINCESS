package com.princess.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.princess.domain.Board;
import com.princess.domain.Reply;
import com.princess.persistence.ReplyRepository;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyRepository replyRepository;
    
	public Reply saveReply(Reply reply) {
		return replyRepository.save(reply);
	}
	
	public Reply saveReReply(Reply reply) {
		reply.setReplyNum(null);
		return replyRepository.save(reply);
	}

	public Page<Reply> getBoard(Board board, Pageable pageable) {
		return replyRepository.findByPostNum(board, pageable);
	}

	public List<Reply> findByBoard(Board board) {
		return replyRepository.findByPostNum(board);
	}

	public void deleteReply(Long id) {
		replyRepository.deleteById(id);

	}

}
