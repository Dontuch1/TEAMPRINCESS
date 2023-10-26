package com.princess.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.princess.domain.Auction;
import com.princess.domain.Product;
import com.princess.persistence.AuctionRepository;

@Service
public class AuctionServiceImpl implements AuctionService {
	
	@Autowired
	AuctionRepository AuctionRepo;
	
	public List<Auction> getAuctionList(Product product) {
		return AuctionRepo.findBypNo(product);
	}
}
