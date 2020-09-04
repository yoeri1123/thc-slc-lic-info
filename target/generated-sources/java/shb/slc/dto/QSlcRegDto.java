package shb.slc.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSlcRegDto is a Querydsl query type for SlcRegDto
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSlcRegDto extends EntityPathBase<SlcRegDto> {

    private static final long serialVersionUID = -1822441407L;

    public static final QSlcRegDto slcRegDto = new QSlcRegDto("slcRegDto");

    public final StringPath delDt = createString("delDt");

    public final StringPath delNm = createString("delNm");

    public final DateTimePath<java.sql.Timestamp> delSdt = createDateTime("delSdt", java.sql.Timestamp.class);

    public final BooleanPath delYn = createBoolean("delYn");

    public final StringPath licNm = createString("licNm");

    public final StringPath licVer = createString("licVer");

    public final NumberPath<Float> purCntrctCnt = createNumber("purCntrctCnt", Float.class);

    public final StringPath purCntrctDt = createString("purCntrctDt");

    public final StringPath purCntrctNo = createString("purCntrctNo");

    public final StringPath purUnit = createString("purUnit");

    public final StringPath regDt = createString("regDt");

    public final StringPath regNm = createString("regNm");

    public final DateTimePath<java.time.LocalDateTime> regSdt = createDateTime("regSdt", java.time.LocalDateTime.class);

    public final NumberPath<Long> slcRegNo = createNumber("slcRegNo", Long.class);

    public final StringPath typeNo = createString("typeNo");

    public final StringPath udtDt = createString("udtDt");

    public final StringPath udtNm = createString("udtNm");

    public final DateTimePath<java.time.LocalDateTime> udtSdt = createDateTime("udtSdt", java.time.LocalDateTime.class);

    public final StringPath validBgndt = createString("validBgndt");

    public final StringPath validEnddt = createString("validEnddt");

    public QSlcRegDto(String variable) {
        super(SlcRegDto.class, forVariable(variable));
    }

    public QSlcRegDto(Path<? extends SlcRegDto> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSlcRegDto(PathMetadata metadata) {
        super(SlcRegDto.class, metadata);
    }

}

