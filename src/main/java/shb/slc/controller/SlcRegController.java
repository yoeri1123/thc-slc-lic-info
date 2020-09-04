package shb.slc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shb.slc.domain.SlcCommonResponse;
import shb.slc.domain.SlcRegDomain;
import shb.slc.domain.SlcRegUpdDomain;
import shb.slc.dto.SlcInfoDto;
import shb.slc.dto.SlcRegDto;
import shb.slc.mapper.SlcRegMapperImpl;
import shb.slc.service.SlcInfoService;
import shb.slc.service.SlcRegService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lic-info/v1/registers")
public class SlcRegController {

    @Autowired
    SlcRegService slcRegService;

    SlcRegMapperImpl slcRegMapper;



    // lic 등록 관리 등록 api -> 사용관리 call해야함.
    // RequestHeader of required option : no essential parameter (희예선임님께 여쭤보기)
    @PostMapping
    public ResponseEntity addLicReg(
            @RequestBody SlcRegDomain slcRegDomain,
            @RequestHeader(name = "login-id") String loginId,
            @RequestHeader(name = "standard-date", required = false) String standardDate,
            @RequestHeader(name = "gid", required = false) String gid,
            @RequestHeader(name = "seq", defaultValue = "0") int seq){

        if(!slcRegService.validateCheckStandardDate(standardDate)){ standardDate = slcRegService.callPrepostStandardDate(); };//call Prepost API
        if(!slcRegService.validateCheckGlobalId(gid)){ slcRegService.callPrePostGid(); };  //call Prepost API

        Boolean result = slcRegService.addLicReg(slcRegDomain, loginId, standardDate, gid, seq);

        // 라이선스 사용관리 call
        return result ? ResponseEntity.ok().body(new SlcCommonResponse("LIC-I-200", "정상처리 되었습니다.")) :
                ResponseEntity.badRequest().body(new SlcCommonResponse("LIC-E-500", "처리 실패했습니다."));

    }
    // lic 정보 관리 수정 api
    @PutMapping
    public ResponseEntity editLicRegister(@RequestBody SlcRegUpdDomain slcRegUpdDomain,
                                      @RequestHeader(name = "login-id") String loginId,
                                      @RequestHeader(name = "standard-date", required = false) String standardDate,
                                      @RequestHeader(name = "gid", required = false) String gid,
                                      @RequestHeader(name = "seq", defaultValue = "0") int seq){

        if(!slcRegService.validateCheckStandardDate(standardDate)){ standardDate = slcRegService.callPrepostStandardDate(); };//call Prepost API
        if(!slcRegService.validateCheckGlobalId(gid)){ slcRegService.callPrePostGid(); };  //call Prepost API

        if(!slcRegService.validateCheckLicRegObject(slcRegUpdDomain.getSlcRegNo())){ return ResponseEntity.ok().body(new SlcCommonResponse("LIC-E-406", "업데이트 할 객체를 찾지 못했습니다.")); }
        // 라이선스 사용관리 call

        Boolean result = slcRegService.editLicReg(slcRegUpdDomain, loginId, standardDate, gid, seq);
        return result ? ResponseEntity.ok().body(new SlcCommonResponse("LIC-I-200", "정상처리 되었습니다.")) :
                ResponseEntity.badRequest().body(new SlcCommonResponse("LIC-E-500", "처리 실패했습니다."));
    }

    //lic 정보 관리 삭제 api
    @DeleteMapping("/{slcRegNo}")
    public ResponseEntity removeLicInfo(@RequestBody Long slcRegNo,
                                        @RequestHeader(name = "login-id") String loginId,
                                        @RequestHeader(name = "standard-date", required = false) String standardDate,
                                        @RequestHeader(name = "gid", required = false) String gid,
                                        @RequestHeader(name = "seq", defaultValue = "0") int seq){

        if(!slcRegService.validateCheckStandardDate(standardDate)){ standardDate = slcRegService.callPrepostStandardDate(); };//call Prepost API
        if(!slcRegService.validateCheckGlobalId(gid)){ slcRegService.callPrePostGid(); };  //call Prepost API

        Boolean result = slcRegService.removeLicReg(slcRegNo, loginId, standardDate, gid, seq);

        return result ? ResponseEntity.ok().body(new SlcCommonResponse("LIC-I-200", "정상처리 되었습니다.")) :
                ResponseEntity.badRequest().body(new SlcCommonResponse("LIC-E-500", "처리 실패했습니다."));

    }

    //lic 기본 정보 상세 조회 api
    @GetMapping("/{slcRegNo}")
    public ResponseEntity slcIzfoDomain(@PathVariable Long slcRegNo,
                                        @RequestHeader(name = "login-id") String loginId,
                                        @RequestHeader(name = "standard-date", required = false) String standardDate,
                                        @RequestHeader(name = "gid", required = false) String gid,
                                        @RequestHeader(name = "seq", defaultValue = "0") int seq){
        if(!slcRegService.validateCheckStandardDate(standardDate)){ standardDate = slcRegService.callPrepostStandardDate(); };//call Prepost API
        if(!slcRegService.validateCheckGlobalId(gid)){ slcRegService.callPrePostGid(); };  //call Prepost API

        SlcRegDomain slcRegDomain = slcRegService.getLicRegDetail(slcRegNo, loginId, standardDate, gid, seq);

        return slcRegDomain != null ? ResponseEntity.ok().body(slcRegDomain) :
                ResponseEntity.badRequest().body(new SlcCommonResponse("LIC-E-500", "처리 실패했습니다."));


    }

    //lic 기본정보 조건 및 다건 조회 api
    @GetMapping
    public ResponseEntity slcInfoDomains(@RequestParam Map<String, String> parameters,
                                         @RequestHeader(name = "login-id") String loginId,
                                         @RequestHeader(name = "standard-date", required = false) String standardDate,
                                         @RequestHeader(name = "gid", required = false) String gid,
                                         @RequestHeader(name = "seq", defaultValue = "0") int seq){

        if(!slcRegService.validateCheckStandardDate(standardDate)){ standardDate = slcRegService.callPrepostStandardDate(); };//call Prepost API
        if(!slcRegService.validateCheckGlobalId(gid)){ slcRegService.callPrePostGid(); };  //call Prepost API
        // page와 size는 web에서 지정해줘야 함.
        Page<SlcRegDto> slcRegDtos = slcRegService.getLicRegQuery(0, 5, parameters, loginId, standardDate, gid, seq);
        return slcRegDtos != null ? ResponseEntity.ok().body(slcRegDtos) :
                ResponseEntity.badRequest().body(new SlcCommonResponse("LIC-E-500", "처리 실패했습니다."));

    }





}
