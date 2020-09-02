package shb.slc.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Web -> SlcInfo (Http Body Entity)
// When used create object
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SlcInfoDomain {
    // Dto <-> Domain
    private String licNm;   //라이선스명
    private String licDtl;  //라이선스 상세설명
    private String licMangNm;   //라이선스 담당자명
    private String licDept; //라이선스 담당부서
    private String licMfact; //라이선스 제조업체
    private String licMaint; //라이선스 유지보수업체
    private String mfactNm; //라이선스 제조업체 영업대표
    private String maintNm; //라이선스 유지보수업체 영업대표

    public SlcInfoDomain(SlcInfoUpdDomain slcInfoUpdDomain){
        this.licNm = slcInfoUpdDomain.getLicNm();
        this.licDtl = slcInfoUpdDomain.getLicDtl();
        this.licMangNm = slcInfoUpdDomain.getLicMangNm();
        this.licDept = slcInfoUpdDomain.getLicDept();
        this.licMfact = slcInfoUpdDomain.getLicMfact();
        this.licMaint = slcInfoUpdDomain.getLicMaint();
        this.mfactNm = slcInfoUpdDomain.getMfactNm();
        this.maintNm = slcInfoUpdDomain.getMaintNm();
    }
}
