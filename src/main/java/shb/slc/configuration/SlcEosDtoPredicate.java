package shb.slc.configuration;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import shb.slc.dto.QSlcEosDto;

import java.util.Map;

public class SlcEosDtoPredicate {
    public static Predicate search(Map<String, String> parameters){
        QSlcEosDto slcEosDto = QSlcEosDto.slcEosDto;

        BooleanBuilder builder = new BooleanBuilder();

        for (String key : parameters.keySet()){
            switch (key){
                case "licNm":
                    builder.and(slcEosDto.licNm.eq(parameters.get(key)));
                    break;
                case "licVer":
                    builder.and(slcEosDto.licVer.eq(parameters.get(key)));
                    break;
                case "licPatchVer":
                    builder.and(slcEosDto.licPatchVer.eq(parameters.get(key)));
                    break;
                case "licEos":
                    builder.and(slcEosDto.licEos.eq(parameters.get(key)));
                    break;
                case "licEosl":
                    builder.and(slcEosDto.licEosl.eq(parameters.get(key)));
                    break;
                case "licEol":
                    builder.and(slcEosDto.licEol.eq(parameters.get(key)));
                    break;
                default:
                    break;
            }
        }
        return builder;
    }
}
