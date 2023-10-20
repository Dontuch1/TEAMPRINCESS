package com.princess.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReview is a Querydsl query type for Review
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReview extends EntityPathBase<Review> {

    private static final long serialVersionUID = -1670052054L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReview review1 = new QReview("review1");

    public final QSales buyId;

    public final StringPath content = createString("content");

    public final QProduct pNo;

    public final EnumPath<CheckCondition.Rating> review = createEnum("review", CheckCondition.Rating.class);

    public final QMember reviewId;

    public final NumberPath<Long> reviewNo = createNumber("reviewNo", Long.class);

    public final DateTimePath<java.util.Date> reviewRegdate = createDateTime("reviewRegdate", java.util.Date.class);

    public QReview(String variable) {
        this(Review.class, forVariable(variable), INITS);
    }

    public QReview(Path<? extends Review> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReview(PathMetadata metadata, PathInits inits) {
        this(Review.class, metadata, inits);
    }

    public QReview(Class<? extends Review> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.buyId = inits.isInitialized("buyId") ? new QSales(forProperty("buyId"), inits.get("buyId")) : null;
        this.pNo = inits.isInitialized("pNo") ? new QProduct(forProperty("pNo"), inits.get("pNo")) : null;
        this.reviewId = inits.isInitialized("reviewId") ? new QMember(forProperty("reviewId")) : null;
    }

}

