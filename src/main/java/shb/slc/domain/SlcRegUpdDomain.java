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
public class SlcRegUpdDomain {
    // Dto <-> Domain
    private Long slcRegNo;  //구매등록번호
    private String purCntrctNo; //구매계약번호
    private String purCntrctDt; //구매계약일자
    private String licNm;   //라이선스명
    private String licVer;  //라이선스버전정보
    private String typeNo;  //유형번호
    private String purUnit; //구매단위
    private float purCntrctCnt; //도입수량

    private String validBgndt;  //유효기간시작일(기간제경우)
    private String validEnddt;  //유효기간종료일(기간제경우)
}
