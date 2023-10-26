package com.princess.service;

import java.util.List;

import com.princess.domain.Auction;
import com.princess.domain.Product;

public interface AuctionService {

	List<Auction> getAuctionList(Product product);

}