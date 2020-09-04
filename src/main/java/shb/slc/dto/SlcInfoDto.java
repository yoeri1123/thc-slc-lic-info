package shb.slc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryEntity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.web.PageableDefault;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name="SLC_SW_INFO")
public class SlcInfoDto {
    //dbio : database input output(domain)
    // 라이선스 정보 관리 (SLC_SW_INFO)
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long licInfoNo;   //라이선스정보번호

    private String licNm; // 라이선스명
    private String licDtl; // 라이선스 상세설명
    private String licMangNm; // 라이선스 담당자명
    private String licDept; //라이선스 담당부서
    private String licMfact; //라이선스 제조업체
    private String licMaint; //라이선스 유지보수업체
    private String mfactNm; //라이선스 제조업체 영업대표
    private String maintNm; //라이선스 유지보수업체 영업대표

    private String regNm; //등록자명
    private String regDt; //등록일시

    public String getLicNm() {
        return licNm;
    }

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
