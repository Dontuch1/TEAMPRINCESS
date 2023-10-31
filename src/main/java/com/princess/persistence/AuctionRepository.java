package com.princess.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.princess.domain.Auction;
import com.princess.domain.Member;
import com.princess.domain.Product;

public interface AuctionRepository extends CrudRepository<Auction, Long> {
                                                                                                                                                                    
	List<Auction> findBypNoOrderByAuctionNoDesc(Product product);

	@Query("select count(a) from Auction a where a.pNo.pNo = :pNo and a.auctionId.id = :id")
    int countByPNoAndId(@Param("pNo") Long pNo, @Param("id") String id);
	
	List<Auction> findByAuctionIdOrderByAuctionPriceDesc(Member member);
}
