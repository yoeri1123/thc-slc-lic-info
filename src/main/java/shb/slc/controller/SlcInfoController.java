package shb.slc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import shb.slc.domain.SlcCommonResponse;
import shb.slc.domain.SlcInfoDomain;
import shb.slc.domain.SlcInfoUpdDomain;
import shb.slc.dto.SlcInfoDto;
import shb.slc.service.SlcInfoService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lic-info/v1/licenses")
public class SlcInfoController {
    // lic 정보 관리 등록 api

    @Autowired
    SlcInfoService slcInfoService;

    @PostMapping
    public ResponseEntity addLicInfo(
            @RequestBody SlcInfoDomain slcInfoDomain,
            @RequestHeader(name = "login-id") String loginId,
            @RequestHeader(name = "standard-date", required = false) String standardDate,
            @RequestHeader(name = "gid", required = false) String gid,
            @RequestHeader(name = "seq", defaultValue = "0") int seq) throws SQLException {

        if(!slcInfoService.validateCheckStandardDate(standardDate)){ standardDate = slcInfoService.callPrepostStandardDate(); };    //call Prepost API
        if(!slcInfoService.validateCheckGlobalId(gid)){ slcInfoService.callPrePostGid(); };  //call Prepost API

        Boolean result = slcInfoService.addLicInfo(slcInfoDomain, loginId, standardDate, gid, seq);

        return result ? ResponseEntity.ok().body(new SlcCommonResponse("LIC-I-200", "정상처리 되었습니다.")) :
                ResponseEntity.badRequest().body(new SlcCommonResponse("LIC-E-500", "처리 실패했습니다."));

    }

    // lic 정보 관리 수정 api
    @PutMapping
    public ResponseEntity editLicInfo(@RequestBody SlcInfoUpdDomain slcInfoUpdDomain,
                                      @RequestHeader(name = "login-id") String loginId,
                                      @RequestHeader(name = "standard-date", required = false) String standardDate,
                                      @RequestHeader(name = "gid", required = false) String gid,
                                      @RequestHeader(name = "seq", defaultValue = "0") int seq) throws SQLException {
        if(!slcInfoService.validateCheckStandardDate(standardDate)){ standardDate = slcInfoService.callPrepostStandardDate(); };//call Prepost API
        if(!slcInfoService.validateCheckGlobalId(gid)){ slcInfoService.callPrePostGid(); };  //call Prepost API
        // find ->
        /* Http Status Code
        * 406 : Not Acceptable, 사용자에이전트에서 정해진 규격에 따른 어떠한 콘텐츠도 찾지 못했을 때 웹서버가 보냄. */
        if(!slcInfoService.validateCheckLicInfoObject(slcInfoUpdDomain.getLicInfoNo())){ return ResponseEntity.ok().body(new SlcCommonResponse("LIC-E-406", "업데이트 할 객체를 찾지 못했습니다.")); }
        Boolean result =slcInfoService.editLicInfo(slcInfoUpdDomain, loginId, standardDate, gid, seq);

        return result ? ResponseEntity.ok().body(new SlcCommonResponse("LIC-I-200", "정상처리 되었습니다.")) :
                ResponseEntity.badRequest().body(new SlcCommonResponse("LIC-E-500", "처리 실패했습니다."));

    }

    //lic 정보 관리 삭제 api
    @DeleteMapping("/{licInfoNo}")
    public ResponseEntity removeLicInfo(@PathVariable Long licInfoNo,
                              @RequestHeader(name = "login-id") String loginId,
                              @RequestHeader(name = "standard-date", required = false) String standardDate,
                              @RequestHeader(name = "gid", required = false) String gid,
                              @RequestHeader(name = "seq", defaultValue = "0") int seq){

        if(!slcInfoService.validateCheckStandardDate(standardDate)){ standardDate = slcInfoService.callPrepostStandardDate(); };//call Prepost API
        if(!slcInfoService.validateCheckGlobalId(gid)){ slcInfoService.callPrePostGid(); };  //call Prepost API

        Boolean result = slcInfoService.removeLicInfo(licInfoNo, loginId, standardDate, gid, seq);

        return result ? ResponseEntity.ok().body(new SlcCommonResponse("LIC-I-200", "정상처리 되었습니다.")) :
                ResponseEntity.badRequest().body(new SlcCommonResponse("LIC-E-500", "처리 실패했습니다."));


    }

    //lic 기본 정보 상세 조회 api
    @GetMapping("/{licInfoNo}")
    public ResponseEntity getLicInfoDetail(@PathVariable Long licInfoNo,
                                       @RequestHeader(name = "login-id") String loginId,
                                       @RequestHeader(name = "standard-date", required = false) String standardDate,
                                       @RequestHeader(name = "gid", required = false) String gid,
                                       @RequestHeader(name = "seq", defaultValue = "0") int seq){
        if(!slcInfoService.validateCheckStandardDate(standardDate)){ standardDate = slcInfoService.callPrepostStandardDate(); };//call Prepost API
        if(!slcInfoService.validateCheckGlobalId(gid)){ slcInfoService.callPrePostGid(); };  //call Prepost API

        SlcInfoDomain slcInfoDomain =  slcInfoService.getLicInfoDetail(licInfoNo, loginId, standardDate, gid, seq);

        return slcInfoDomain != null ? ResponseEntity.ok().body(slcInfoDomain) :
                ResponseEntity.badRequest().body(new SlcCommonResponse("LIC-E-500", "처리 실패했습니다."));

    }

    //lic 기본정보 조건 및 다건 조회 api
    @GetMapping
    public ResponseEntity slcInfoDomains(@RequestParam Map<String, String> parameters,
                                         @RequestHeader(name = "login-id") String loginId,
                                         @RequestHeader(name = "standard-date", required = false) String standardDate,
                                         @RequestHeader(name = "gid", required = false) String gid,
                                         @RequestHeader(name = "seq", defaultValue = "0") int seq){

        if(!slcInfoService.validateCheckStandardDate(standardDate)){ standardDate = slcInfoService.callPrepostStandardDate(); };//call Prepost API
        if(!slcInfoService.validateCheckGlobalId(gid)){ slcInfoService.callPrePostGid(); };  //call Prepost API
        // page와 size는 web에서 지정해줘야 함.
        Page<SlcInfoDto> slcInfoDtoPage = slcInfoService.getLicInfoQuery(0, 5, parameters, loginId, standardDate, gid, seq);
        return slcInfoDtoPage != null ? ResponseEntity.ok().body(slcInfoDtoPage) :
                ResponseEntity.badRequest().body(new SlcCommonResponse("LIC-E-500", "처리 실패했습니다."));


    }


}
