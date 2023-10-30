package com.princess.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.princess.domain.Board;
import com.princess.domain.Reply;

@Repository
public interface ReplyRepository extends CrudRepository<Reply, Long> {
	
	List<Reply> findByPostNum(Board board);
}
