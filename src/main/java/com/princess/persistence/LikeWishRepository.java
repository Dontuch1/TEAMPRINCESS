package com.princess.persistence;

import java.util.List;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.princess.domain.CheckCondition.Type;
import com.princess.domain.LikeWish;
import com.princess.domain.Member;
import com.princess.domain.Product;

public interface LikeWishRepository extends CrudRepository<LikeWish, Long>, QuerydslPredicateExecutor<Product> {
	List<LikeWish> findByLikeIdAndType(Member member, Type type);
}
