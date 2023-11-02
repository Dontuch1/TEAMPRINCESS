package com.princess.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.princess.domain.Board;
import com.princess.domain.Member;

public interface BoardRepository extends CrudRepository<Board, Long>, QuerydslPredicateExecutor<Board>{

	Page<Board> findByUserId(Member member, Pageable pageable);
	
	@Query("SELECT b FROM Board b")
	Page<Board> getBoardList(Pageable pageable);

}