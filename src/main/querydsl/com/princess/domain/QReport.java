package com.princess.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReport is a Querydsl query type for Report
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReport extends EntityPathBase<Report> {

    private static final long serialVersionUID = -1670224634L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReport report = new QReport("report");

    public final NumberPath<Long> postNo = createNumber("postNo", Long.class);

    public final StringPath rptCon = createString("rptCon");

    public final DatePath<java.util.Date> rptDate = createDate("rptDate", java.util.Date.class);

    public final QMember rptId;

    public final NumberPath<Long> rptNo = createNumber("rptNo", Long.class);

    public final EnumPath<CheckCondition.Type> type = createEnum("type", CheckCondition.Type.class);

    public QReport(String variable) {
        this(Report.class, forVariable(variable), INITS);
    }

    public QReport(Path<? extends Report> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReport(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReport(PathMetadata metadata, PathInits inits) {
        this(Report.class, metadata, inits);
    }

    public QReport(Class<? extends Report> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.rptId = inits.isInitialized("rptId") ? new QMember(forProperty("rptId")) : null;
    }

}

