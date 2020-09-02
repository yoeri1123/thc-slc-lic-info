package shb.slc.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SlcEosPK implements Serializable {
    private String licNm;    //라이선스명
    private String licVer; //라이선스버전정보
}
