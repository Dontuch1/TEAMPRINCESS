package com.princess.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -1813472660L;

    public static final QMember member = new QMember("member1");

    public final EnumPath<CheckCondition.YorN> agree = createEnum("agree", CheckCondition.YorN.class);

    public final ListPath<Auction, QAuction> auctionList = this.<Auction, QAuction>createList("auctionList", Auction.class, QAuction.class, PathInits.DIRECT2);

    public final ListPath<Auth, QAuth> authList = this.<Auth, QAuth>createList("authList", Auth.class, QAuth.class, PathInits.DIRECT2);

    public final NumberPath<Integer> battry = createNumber("battry", Integer.class);

    public final DateTimePath<java.util.Date> birth = createDateTime("birth", java.util.Date.class);

    public final ListPath<Board, QBoard> boardList = this.<Board, QBoard>createList("boardList", Board.class, QBoard.class, PathInits.DIRECT2);

    public final NumberPath<Integer> deposit = createNumber("deposit", Integer.class);

    public final StringPath email = createString("email");

    public final StringPath id = createString("id");

    public final ListPath<LikeWish, QLikeWish> likeWishList = this.<LikeWish, QLikeWish>createList("likeWishList", LikeWish.class, QLikeWish.class, PathInits.DIRECT2);

    public final StringPath location = createString("location");

    public final StringPath nickName = createString("nickName");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final ListPath<Product, QProduct> productList = this.<Product, QProduct>createList("productList", Product.class, QProduct.class, PathInits.DIRECT2);

    public final DateTimePath<java.util.Date> regdate = createDateTime("regdate", java.util.Date.class);

    public final ListPath<Reply, QReply> replyList = this.<Reply, QReply>createList("replyList", Reply.class, QReply.class, PathInits.DIRECT2);

    public final ListPath<Report, QReport> reportList = this.<Report, QReport>createList("reportList", Report.class, QReport.class, PathInits.DIRECT2);

    public final ListPath<Review, QReview> reviewList = this.<Review, QReview>createList("reviewList", Review.class, QReview.class, PathInits.DIRECT2);

    public final ListPath<Sales, QSales> salesList = this.<Sales, QSales>createList("salesList", Sales.class, QSales.class, PathInits.DIRECT2);

    public final StringPath userName = createString("userName");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

