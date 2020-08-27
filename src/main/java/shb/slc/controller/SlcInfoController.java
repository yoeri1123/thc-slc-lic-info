package shb.slc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import shb.slc.domain.SlcInfoDomain;
import shb.slc.dto.SlcInfoDto;
import shb.slc.service.SlcInfoService;

import java.util.Map;

@RestController
@RequestMapping("/lic-info/v1/licenses")
public class SlcInfoController {

    @Autowired
    SlcInfoService slcInfoService;

    // lic 정보 관리 등록 api
    // RequestHeader of required option : no essential parameter (희예선임님께 여쭤보기)
    @PostMapping
    public ResponseEntity addLicInfo(
            @RequestBody SlcInfoDomain slcInfoDomain,
            @RequestHeader(name = "login-id") String loginId,
            @RequestHeader(name = "standard-date", required = false) String standardDate,
            @RequestHeader(name = "gid", required = false) String gid,
            @RequestHeader(name = "seq", defaultValue = "0") int seq){

        if(!slcInfoService.validateCheckStandardDate(standardDate)){ };//call Prepost API
        if(!slcInfoService.validateCheckGlobalId(gid)){ };  //call Prepost API


        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    // lic 정보 관리 수정 api
    @PutMapping
    public void editLicInfo(@RequestBody SlcInfoDomain slcInfoDomain,
                            @RequestHeader(name = "login-id") String loginId,
                            @RequestHeader(name = "standard-date", required = false) String standardDate,
                            @RequestHeader(name = "gid", required = false) String gid,
                            @RequestHeader(name = "seq", defaultValue = "0") int seq){

    }

    //lic 정보 관리 삭제 api
    @DeleteMapping("/{licInfoNo}")
    public void removeLicInfo(@RequestBody SlcInfoDomain slcInfoDomain,
                              @RequestHeader(name = "login-id") String loginId,
                              @RequestHeader(name = "standard-date", required = false) String standardDate,
                              @RequestHeader(name = "gid", required = false) String gid,
                              @RequestHeader(name = "seq", defaultValue = "0") int seq){

    }

    //lic 기본 정보 상세 조회 api
    @GetMapping("/{licInfoNo}")
    public SlcInfoDomain slcInfoDomain(@PathVariable String licInfoNo,
                                       @RequestHeader(name = "login-id") String loginId,
                                       @RequestHeader(name = "standard-date", required = false) String standardDate,
                                       @RequestHeader(name = "gid", required = false) String gid,
                                       @RequestHeader(name = "seq", defaultValue = "0") int seq){

    }

    //lic 기본정보 조건 및 다건 조회 api
    @GetMapping("/licenses")
    public Iterable<SlcInfoDomain> slcInfoDomains(@RequestParam Map<String, String> parameters){
        // 다건조회는 어떻게 하는 게 좋은가..

    }


}
