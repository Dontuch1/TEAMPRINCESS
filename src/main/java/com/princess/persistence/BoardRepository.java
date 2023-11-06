package com.princess.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.princess.domain.Board;
import com.princess.domain.CheckCondition.Display;
import com.princess.domain.Member;

public interface BoardRepository extends CrudRepository<Board, Long>, QuerydslPredicateExecutor<Board>{

	Page<Board> findByUserIdAndDisplay(Member member, Pageable pageable, Display Y);
	
	@Query("SELECT b FROM Board b")
	Page<Board> getBoardList(Pageable pageable);

	Board findBypostNum(Long greatNum);

}