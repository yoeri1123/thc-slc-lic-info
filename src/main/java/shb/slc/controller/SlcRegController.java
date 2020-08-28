package shb.slc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shb.slc.domain.SlcRegDomain;
import shb.slc.service.SlcRegService;

import java.util.Map;

@RestController
@RequestMapping("/lic-info/v1/registers")
public class SlcRegController {

    @Autowired
    SlcRegService slcRegService;

    // lic 등록 관리 등록 api -> 사용관리 call해야함.
    // RequestHeader of required option : no essential parameter (희예선임님께 여쭤보기)
    @PostMapping
    public ResponseEntity addLicRegister(
            @RequestBody SlcRegDomain slcRegDomain,
            @RequestHeader(name = "login-id") String loginId,
            @RequestHeader(name = "standard-date", required = false) String standardDate,
            @RequestHeader(name = "gid", required = false) String gid,
            @RequestHeader(name = "seq", defaultValue = "0") int seq){

        if(!slcRegService.validateCheckStandardDate(standardDate)){ };//call Prepost API
        if(!slcRegService.validateCheckGlobalId(gid)){ };  //call Prepost API

        // 라이선스 사용관리 call

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
    // lic 정보 관리 수정 api
    @PutMapping
    public ResponseEntity editLicRegister(@RequestBody SlcRegDomain slcRegDomain,
                                      @RequestHeader(name = "login-id") String loginId,
                                      @RequestHeader(name = "standard-date", required = false) String standardDate,
                                      @RequestHeader(name = "gid", required = false) String gid,
                                      @RequestHeader(name = "seq", defaultValue = "0") int seq){

        if(!slcRegService.validateCheckStandardDate(standardDate)){ };//call Prepost API
        if(!slcRegService.validateCheckGlobalId(gid)){ };  //call Prepost API

        // 라이선스 사용관리 call

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
    // -----컬럼 명세서 보고 변수명 정의(08.27) ----------------
    //lic 정보 관리 삭제 api
    @DeleteMapping("/{slcRegNo}")
    public ResponseEntity removeLicInfo(@RequestBody SlcRegDomain slcRegNo,
                                        @RequestHeader(name = "login-id") String loginId,
                                        @RequestHeader(name = "standard-date", required = false) String standardDate,
                                        @RequestHeader(name = "gid", required = false) String gid,
                                        @RequestHeader(name = "seq", defaultValue = "0") int seq){
        return ResponseEntity.status(HttpStatus.CREATED).body(null);

    }

    //lic 기본 정보 상세 조회 api
    @GetMapping("/{slcRegNo}")
    public ResponseEntity slcIzfoDomain(@PathVariable String slcRegNo,
                                        @RequestHeader(name = "login-id") String loginId,
                                        @RequestHeader(name = "standard-date", required = false) String standardDate,
                                        @RequestHeader(name = "gid", required = false) String gid,
                                        @RequestHeader(name = "seq", defaultValue = "0") int seq){
        return ResponseEntity.status(HttpStatus.CREATED).body(null);

    }

    //lic 기본정보 조건 및 다건 조회 api
    @GetMapping("/licenses")
    public ResponseEntity slcInfoDomains(@RequestParam Map<String, String> parameters){
        // 다건조회는 어떻게 하는 게 좋은가..
        return ResponseEntity.status(HttpStatus.CREATED).body(null);

    }





}
