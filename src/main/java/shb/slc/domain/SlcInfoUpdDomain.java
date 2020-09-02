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
public class SlcInfoUpdDomain {
    // Dto <-> Domain
    private Long licInfoNo;   //라이선스정보번호
    private String licNm;   //라이선스명
    private String licDtl;  //라이선스 상세설명
    private String licMangNm;   //라이선스 담당자명
    private String licDept; //라이선스 담당부서
    private String licMfact; //라이선스 제조업체
    private String licMaint; //라이선스 유지보수업체
    private String mfactNm; //라이선스 제조업체 영업대표
    private String maintNm; //라이선스 유지보수업체 영업대표

}
