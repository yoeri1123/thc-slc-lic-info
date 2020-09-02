package shb.slc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Entity
@IdClass(SlcEosPK.class)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name="SLC_EOS_MGNT")
public class SlcEosDto {
    // 라이선스 EOS 관리 (SLC_EOS_MGNT)
    @Id
    private String licNm;    //라이선스명
    @Id
    private String licVer; //라이선스버전정보

    private String licPatchVer; //라이선스패치버전정보
    private String licEos;  //라이선스 판매중지기한
    private String licEosl; //라이선스 기술지원중지기한
    private String licEol;  //라이선스 단종기한


    private String regNm; //등록자명
    private String regDt; //등록일시
    private String udtNm; //갱신자명
    private String udtDt; //갱신일시
    private String delNm; //폐기자명
    private String delDt; //폐기일시
    private Boolean delYn; //폐기여부

    @CreationTimestamp
    private LocalDateTime regSdt;   //등록일시(서버시간)
    @UpdateTimestamp
    private LocalDateTime udtSdt; //갱신일시(서버시간)

    private Timestamp delSdt;   //폐기일시(서버시간)



}
