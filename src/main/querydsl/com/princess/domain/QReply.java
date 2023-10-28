package com.princess.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReply is a Querydsl query type for Reply
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReply extends EntityPathBase<Reply> {

    private static final long serialVersionUID = -330972968L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReply reply = new QReply("reply");

    public final QBoard postNum;

    public final QReply reference;

    public final ListPath<Reply, QReply> referenceList = this.<Reply, QReply>createList("referenceList", Reply.class, QReply.class, PathInits.DIRECT2);

    public final DatePath<java.util.Date> regdate = createDate("regdate", java.util.Date.class);

    public final StringPath replyContent = createString("replyContent");

    public final NumberPath<Long> replyNum = createNumber("replyNum", Long.class);

    public final QMember userId;

    public QReply(String variable) {
        this(Reply.class, forVariable(variable), INITS);
    }

    public QReply(Path<? extends Reply> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReply(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReply(PathMetadata metadata, PathInits inits) {
        this(Reply.class, metadata, inits);
    }

    public QReply(Class<? extends Reply> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.postNum = inits.isInitialized("postNum") ? new QBoard(forProperty("postNum"), inits.get("postNum")) : null;
        this.reference = inits.isInitialized("reference") ? new QReply(forProperty("reference"), inits.get("reference")) : null;
        this.userId = inits.isInitialized("userId") ? new QMember(forProperty("userId")) : null;
    }

}

