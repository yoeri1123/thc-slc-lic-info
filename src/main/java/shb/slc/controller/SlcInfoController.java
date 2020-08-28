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


//        return result ? ResponseEntity.ok().body(new SlcCommonResponse("LIC-I-200", "정상처리 되었습니다.")) :
//                ResponseEntity.badRequest().body(new SlcCommonResponse("LIC-E-500", "처리 실패했습니다."));

        return ResponseEntity.ok().body("");
    }

    // lic 정보 관리 수정 api
    @PutMapping
    public ResponseEntity editLicInfo(@RequestBody SlcInfoDomain slcInfoDomain,
                            @RequestHeader(name = "login-id") String loginId,
                            @RequestHeader(name = "standard-date", required = false) String standardDate,
                            @RequestHeader(name = "gid", required = false) String gid,
                            @RequestHeader(name = "seq", defaultValue = "0") int seq){
        return ResponseEntity.ok().body("");
    }

    //lic 정보 관리 삭제 api
    @DeleteMapping("/{licInfoNo}")
    public ResponseEntity removeLicInfo(@RequestBody SlcInfoDomain slcInfoDomain,
                              @RequestHeader(name = "login-id") String loginId,
                              @RequestHeader(name = "standard-date", required = false) String standardDate,
                              @RequestHeader(name = "gid", required = false) String gid,
                              @RequestHeader(name = "seq", defaultValue = "0") int seq){

    }

    //lic 기본 정보 상세 조회 api
    @GetMapping("/{licInfoNo}")
    public ResponseEntity getLicInfoDetail(@PathVariable String licInfoNo,
                                       @RequestHeader(name = "login-id") String loginId,
                                       @RequestHeader(name = "standard-date", required = false) String standardDate,
                                       @RequestHeader(name = "gid", required = false) String gid,
                                       @RequestHeader(name = "seq", defaultValue = "0") int seq){
        return ResponseEntity.ok().body("");

    }

    //lic 기본정보 조건 및 다건 조회 api0
    @GetMapping("/licenses")
    public ResponseEntity slcInfoDomains(@RequestParam Map<String, String> parameters){
        // 다건조회는 어떻게 하는 게 좋은가..
        return ResponseEntity.ok().body("");

    }

    //


}
