package shb.slc.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Web -> SlcInfo (Http Body Entity)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SlcEosDomain {
    // Dto <-> Domain
    private String licNm;   //라이선스명
    private String licVer;  //라이선스버전정보
    private String licPatchVer; //라이선스패치버전정보
    private String licEos;  //라이선스 판매중지기한
    private String licEosl; //라이선스 기술지원중지기한
    private String licEol;  //라이선스 단종기한

}
