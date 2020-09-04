package shb.slc.configuration;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.extern.slf4j.Slf4j;
import shb.slc.dto.QSlcInfoDto;

import java.util.Map;

@Slf4j
public class SlcInfoDtoPredicate {
    public static Predicate search (Map<String, String> parameters){
        QSlcInfoDto slcInfoDto = QSlcInfoDto.slcInfoDto;

        log.info("========builder" );
        BooleanBuilder builder = new BooleanBuilder();

        for(String key : parameters.keySet()){
            switch(key){
                case "licNm":
                    builder.and(slcInfoDto.licNm.eq(parameters.get(key)));
                    break;
                case "licDtl":
                    builder.and(slcInfoDto.licDtl.eq(parameters.get(key)));
                    break;
                case "licMangNm":
                    builder.and(slcInfoDto.licMangNm.eq(parameters.get(key)));
                    break;
                case "licDept":
                    builder.and(slcInfoDto.licDept.eq(parameters.get(key)));
                    break;
                case "licMfact":
                    builder.and(slcInfoDto.licMfact.eq(parameters.get(key)));
                    break;
                case "licMaint":
                    builder.and(slcInfoDto.licMaint.eq(parameters.get(key)));
                    break;
                case "mfactNm":
                    builder.and(slcInfoDto.mfactNm.eq(parameters.get(key)));
                    break;
                case "maintNm" :
                    builder.and(slcInfoDto.maintNm.eq(parameters.get(key)));
                    break;
                default:
                    break;
            }
        };

        return builder;
    }

}
