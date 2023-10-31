package com.princess.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.princess.domain.Board;
import com.princess.domain.Member;
import com.princess.domain.Reply;
import com.princess.persistence.MemberRepository;
import com.princess.persistence.ReplyRepository;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyRepository replyRepository;
    
	@Override
	public Reply saveReply(Reply reply) {
		return replyRepository.save(reply);
	}

	@Override
	public Page<Reply> findByPostNum(Board board, Pageable pageable) {
		return replyRepository.findByPostNum(board, pageable);
	}

	@Override
	public List<Reply> findByBoard(Board board) {
		return replyRepository.findByPostNum(board);
	}

	@Override
	public void deleteReply(Long id) {
		replyRepository.deleteById(id);

	}

}
