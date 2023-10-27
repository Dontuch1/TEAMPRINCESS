package com.princess.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.princess.domain.Auction;
import com.princess.domain.Product;

public interface AuctionRepository extends CrudRepository<Auction, Long> {

	List<Auction> findBypNo(Product product);
}
