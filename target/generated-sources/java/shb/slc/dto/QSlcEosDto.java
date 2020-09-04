package shb.slc.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSlcEosDto is a Querydsl query type for SlcEosDto
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSlcEosDto extends EntityPathBase<SlcEosDto> {

    private static final long serialVersionUID = 2109939628L;

    public static final QSlcEosDto slcEosDto = new QSlcEosDto("slcEosDto");

    public final StringPath delDt = createString("delDt");

    public final StringPath delNm = createString("delNm");

    public final DateTimePath<java.sql.Timestamp> delSdt = createDateTime("delSdt", java.sql.Timestamp.class);

    public final BooleanPath delYn = createBoolean("delYn");

    public final StringPath licEol = createString("licEol");

    public final StringPath licEos = createString("licEos");

    public final StringPath licEosl = createString("licEosl");

    public final StringPath licNm = createString("licNm");

    public final StringPath licPatchVer = createString("licPatchVer");

    public final StringPath licVer = createString("licVer");

    public final StringPath regDt = createString("regDt");

    public final StringPath regNm = createString("regNm");

    public final DateTimePath<java.time.LocalDateTime> regSdt = createDateTime("regSdt", java.time.LocalDateTime.class);

    public final StringPath udtDt = createString("udtDt");

    public final StringPath udtNm = createString("udtNm");

    public final DateTimePath<java.time.LocalDateTime> udtSdt = createDateTime("udtSdt", java.time.LocalDateTime.class);

    public QSlcEosDto(String variable) {
        super(SlcEosDto.class, forVariable(variable));
    }

    public QSlcEosDto(Path<? extends SlcEosDto> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSlcEosDto(PathMetadata metadata) {
        super(SlcEosDto.class, metadata);
    }

}

