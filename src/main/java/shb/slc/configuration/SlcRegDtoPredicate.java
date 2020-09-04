package shb.slc.configuration;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import shb.slc.dto.QSlcRegDto;

import java.util.Map;

public class SlcRegDtoPredicate {
    public static Predicate search(Map<String, String> parameters) {
        QSlcRegDto slcRegDto = QSlcRegDto.slcRegDto;

        BooleanBuilder builder = new BooleanBuilder();

        for(String key : parameters.keySet()){
            switch(key){
                case "purCntrctNo":
                    builder.and(slcRegDto.purCntrctNo.eq(parameters.get(key)));
                    break;
                case "purCntrctDt":
                    builder.and(slcRegDto.purCntrctDt.eq(parameters.get(key)));
                    break;
                case "licNm":
                    builder.and(slcRegDto.licNm.eq(parameters.get(key)));
                    break;
                case "licVer":
                    builder.and(slcRegDto.licVer.eq(parameters.get(key)));
                    break;
                case "typeNo":
                    builder.and(slcRegDto.typeNo.eq(parameters.get(key)));
                    break;
                case "purUnit":
                    builder.and(slcRegDto.purUnit.eq(parameters.get(key)));
                    break;
                case "purCntrctCnt":
                    builder.and(slcRegDto.purCntrctCnt.eq(Float.valueOf(parameters.get(key))));
                    break;
                case "validBgndt":
                    builder.and(slcRegDto.validBgndt.eq(parameters.get(key)));
                    break;
                case "validEnddt":
                    builder.and(slcRegDto.validEnddt.eq(parameters.get(key)));
                default:
                    break;

            }
        }
        return builder;
    }
}