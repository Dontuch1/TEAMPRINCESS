package com.princess.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLikeWish is a Querydsl query type for LikeWish
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLikeWish extends EntityPathBase<LikeWish> {

    private static final long serialVersionUID = -1533176784L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLikeWish likeWish = new QLikeWish("likeWish");

    public final QMember likeId;

    public final NumberPath<Long> likeSeq = createNumber("likeSeq", Long.class);

    public final DateTimePath<java.util.Date> regdate = createDateTime("regdate", java.util.Date.class);

    public final EnumPath<CheckCondition.Type> type = createEnum("type", CheckCondition.Type.class);

    public QLikeWish(String variable) {
        this(LikeWish.class, forVariable(variable), INITS);
    }

    public QLikeWish(Path<? extends LikeWish> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLikeWish(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLikeWish(PathMetadata metadata, PathInits inits) {
        this(LikeWish.class, metadata, inits);
    }

    public QLikeWish(Class<? extends LikeWish> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.likeId = inits.isInitialized("likeId") ? new QMember(forProperty("likeId")) : null;
    }

}

