package com.princess.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = -1641433251L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProduct product = new QProduct("product");

    public final NumberPath<Integer> AucDuration = createNumber("AucDuration", Integer.class);

    public final EnumPath<CheckCondition.YorN> auction = createEnum("auction", CheckCondition.YorN.class);

    public final ListPath<Auction, QAuction> auctionList = this.<Auction, QAuction>createList("auctionList", Auction.class, QAuction.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    public final EnumPath<CheckCondition.YorN> delevery = createEnum("delevery", CheckCondition.YorN.class);

    public final EnumPath<CheckCondition.Display> display = createEnum("display", CheckCondition.Display.class);

    public final EnumPath<CheckCondition.PCategory> pCategory = createEnum("pCategory", CheckCondition.PCategory.class);

    public final NumberPath<Long> pNo = createNumber("pNo", Long.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final DatePath<java.util.Date> regdate = createDate("regdate", java.util.Date.class);

    public final ListPath<Review, QReview> reviewList = this.<Review, QReview>createList("reviewList", Review.class, QReview.class, PathInits.DIRECT2);

    public final QMember salesId;

    public final ListPath<Sales, QSales> salesList = this.<Sales, QSales>createList("salesList", Sales.class, QSales.class, PathInits.DIRECT2);

    public final EnumPath<CheckCondition.YorN> sold = createEnum("sold", CheckCondition.YorN.class);

    public final StringPath title = createString("title");

    public final StringPath upload = createString("upload");

    public QProduct(String variable) {
        this(Product.class, forVariable(variable), INITS);
    }

    public QProduct(Path<? extends Product> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProduct(PathMetadata metadata, PathInits inits) {
        this(Product.class, metadata, inits);
    }

    public QProduct(Class<? extends Product> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.salesId = inits.isInitialized("salesId") ? new QMember(forProperty("salesId")) : null;
    }

}

