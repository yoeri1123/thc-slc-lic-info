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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name="SLC_REG_INFO")
public class SlcRegDto {
    // 라이선스 등록 관리 (SLC_REG_INFO)
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long slcRegNo;    //구매등록번호

    private String purCntrctNo; //구매계약번호
    private String purCntrctDt; //구매계약일자
    private String licNm;   //라이선스명
    private String licVer;  //라이선스버전정보
    private String typeNo;  //유형번호
    private String purUnit; //구매단위
    private float purCntrctCnt; //도입수량

    private String validBgndt;  //유효기간시작일(기간제경우)
    private String validEnddt;  //유효기간종료일(기간제경우)

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
