package shb.slc.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSlcInfoDto is a Querydsl query type for SlcInfoDto
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSlcInfoDto extends EntityPathBase<SlcInfoDto> {

    private static final long serialVersionUID = 199266683L;

    public static final QSlcInfoDto slcInfoDto = new QSlcInfoDto("slcInfoDto");

    public final StringPath delDt = createString("delDt");

    public final StringPath delNm = createString("delNm");

    public final DateTimePath<java.sql.Timestamp> delSdt = createDateTime("delSdt", java.sql.Timestamp.class);

    public final BooleanPath delYn = createBoolean("delYn");

    public final StringPath licDept = createString("licDept");

    public final StringPath licDtl = createString("licDtl");

    public final NumberPath<Long> licInfoNo = createNumber("licInfoNo", Long.class);

    public final StringPath licMaint = createString("licMaint");

    public final StringPath licMangNm = createString("licMangNm");

    public final StringPath licMfact = createString("licMfact");

    public final StringPath licNm = createString("licNm");

    public final StringPath maintNm = createString("maintNm");

    public final StringPath mfactNm = createString("mfactNm");

    public final StringPath regDt = createString("regDt");

    public final StringPath regNm = createString("regNm");

    public final DateTimePath<java.time.LocalDateTime> regSdt = createDateTime("regSdt", java.time.LocalDateTime.class);

    public final StringPath udtDt = createString("udtDt");

    public final StringPath udtNm = createString("udtNm");

    public final DateTimePath<java.time.LocalDateTime> udtSdt = createDateTime("udtSdt", java.time.LocalDateTime.class);

    public QSlcInfoDto(String variable) {
        super(SlcInfoDto.class, forVariable(variable));
    }

    public QSlcInfoDto(Path<? extends SlcInfoDto> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSlcInfoDto(PathMetadata metadata) {
        super(SlcInfoDto.class, metadata);
    }

}

