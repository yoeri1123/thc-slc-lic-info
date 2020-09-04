package shb.slc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SlcEosPK implements Serializable {
    private String licNm;    //라이선스명
    private String licVer; //라이선스버전정보
}
