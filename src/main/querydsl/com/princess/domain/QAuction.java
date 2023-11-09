package com.princess.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAuction is a Querydsl query type for Auction
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAuction extends EntityPathBase<Auction> {

    private static final long serialVersionUID = -1993815887L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAuction auction = new QAuction("auction");

    public final QMember auctionId;

    public final NumberPath<Long> auctionNo = createNumber("auctionNo", Long.class);

    public final NumberPath<Integer> auctionPrice = createNumber("auctionPrice", Integer.class);

    public final QProduct pNo;

    public final DatePath<java.util.Date> regdate = createDate("regdate", java.util.Date.class);

    public QAuction(String variable) {
        this(Auction.class, forVariable(variable), INITS);
    }

    public QAuction(Path<? extends Auction> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAuction(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAuction(PathMetadata metadata, PathInits inits) {
        this(Auction.class, metadata, inits);
    }

    public QAuction(Class<? extends Auction> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.auctionId = inits.isInitialized("auctionId") ? new QMember(forProperty("auctionId")) : null;
        this.pNo = inits.isInitialized("pNo") ? new QProduct(forProperty("pNo"), inits.get("pNo")) : null;
    }

}

