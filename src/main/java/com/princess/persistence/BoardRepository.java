package com.princess.persistence;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.princess.domain.Board;

public interface BoardRepository extends CrudRepository<Board, Long>, QuerydslPredicateExecutor<Board>{





}