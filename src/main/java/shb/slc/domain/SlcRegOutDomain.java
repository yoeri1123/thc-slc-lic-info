package shb.slc.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SlcRegOutDomain {
    private String licNm;   //라이선스명
    private String licVer;  //라이선스버전정보
    private float purCntrctCnt; //도입수량
}
