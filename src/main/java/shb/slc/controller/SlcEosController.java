package shb.slc.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shb.slc.domain.SlcCommonResponse;
import shb.slc.domain.SlcEosDomain;
import shb.slc.dto.SlcEosDto;
import shb.slc.service.SlcEosService;

import java.util.Map;

@RestController
@RequestMapping("/lic-info/v1/liceos")
public class SlcEosController {
    // lic eos 관리 등록 api

    @Autowired
    SlcEosService slcEosService;

    @PostMapping
    public ResponseEntity addLicEos(@RequestBody SlcEosDomain slcEosDomain,
                                    @RequestHeader(name = "login-id") String loginId,
                                    @RequestHeader(name = "standard-date", required = false) String standardDate,
                                    @RequestHeader(name = "gid", required = false) String gid,
                                    @RequestHeader(name = "seq", defaultValue = "0") int seq){
        if(!slcEosService.validateCheckStandardDate(standardDate)){ standardDate = slcEosService.callPrepostStandardDate(); }
        if(!slcEosService.validateCheckGlobalId(gid)){ slcEosService.callPrePostGid(); }

        Boolean result = slcEosService.addLicEos(slcEosDomain, loginId, standardDate, gid, seq);

        return result ? ResponseEntity.ok().body(new SlcCommonResponse("LIC-I-200", "정상처리 되었습니다.")) :
                ResponseEntity.badRequest().body(new SlcCommonResponse("LIC-E-500", "처리 실패했습니다."));
    }

    @PutMapping
    public ResponseEntity editLicEos(@RequestBody SlcEosDto slcEosDto,
                                     @RequestHeader(name = "login-id") String loginId,
                                     @RequestHeader(name = "standard-date", required = false) String standardDate,
                                     @RequestHeader(name = "gid", required = false) String gid,
                                     @RequestHeader(name = "seq", defaultValue = "0") int seq){
        if(!slcEosService.validateCheckStandardDate(standardDate)){ standardDate = slcEosService.callPrepostStandardDate(); }
        if(!slcEosService.validateCheckGlobalId(gid)){ slcEosService.callPrePostGid(); }

        // find ->
        /* Http Status Code
         * 406 : Not Acceptable, 사용자에이전트에서 정해진 규격에 따른 어떠한 콘텐츠도 찾지 못했을 때 웹서버가 보냄. */
        if(!slcEosService.validateCheckLicEosObject(slcEosDto.getLicNm(), slcEosDto.getLicVer())){ return ResponseEntity.ok().body(new SlcCommonResponse("LIC-E-406", "업데이트 할 객체를 찾지 못했습니다.")); }

        Boolean result = slcEosService.editLicEos(slcEosDto, loginId, standardDate, gid, seq);

        return result ? ResponseEntity.ok().body(new SlcCommonResponse("LIC-I-200", "정상처리 되었습니다.")) :
                ResponseEntity.badRequest().body(new SlcCommonResponse("LIC-E-500", "처리 실패했습니다."));

    }
    // lic eos 정보 관리 삭제 api
    @DeleteMapping("/{licNm}/{licVer}")
    public ResponseEntity removeLicEos(@PathVariable String licNm,
                                        @PathVariable String licVer,
                                        @RequestHeader(name = "login-id") String loginId,
                                        @RequestHeader(name = "standard-date", required = false) String standardDate,
                                        @RequestHeader(name = "gid", required = false) String gid,
                                        @RequestHeader(name = "seq", defaultValue = "0") int seq){
        if(!slcEosService.validateCheckStandardDate(standardDate)){ standardDate = slcEosService.callPrepostStandardDate(); };//call Prepost API
        if(!slcEosService.validateCheckGlobalId(gid)){ slcEosService.callPrePostGid(); };  //call Prepost API

        Boolean result = slcEosService.removeLicInfo(licNm, licVer, loginId, standardDate, gid, seq);

        return result ? ResponseEntity.ok().body(new SlcCommonResponse("LIC-I-200", "정상처리 되었습니다.")) :
                ResponseEntity.badRequest().body(new SlcCommonResponse("LIC-E-500", "처리 실패했습니다."));
    }

    // lic eos 기본정보 상세 조회 api
    @GetMapping("/{licNm}/{licVer}")
    public ResponseEntity getLicEosDetail(@PathVariable String licNm,
                                          @PathVariable String licVer,
                                          @RequestHeader(name = "login-id") String loginId,
                                          @RequestHeader(name = "standard-date", required = false) String standardDate,
                                          @RequestHeader(name = "gid", required = false) String gid,
                                          @RequestHeader(name = "seq", defaultValue = "0") int seq){
        if(!slcEosService.validateCheckStandardDate(standardDate)){ standardDate = slcEosService.callPrepostStandardDate(); }
        if(!slcEosService.validateCheckGlobalId(gid)){ gid = slcEosService.callPrePostGid(); }

        SlcEosDomain slcEosDomain = slcEosService.getEosDetail(licNm, licVer, loginId, standardDate, gid, seq);

        return slcEosDomain != null ? ResponseEntity.ok().body(slcEosDomain) :
                ResponseEntity.badRequest().body(new SlcCommonResponse("LIC-E-500", "처리 실패했습니다."));

    }

    // lic eos 기본정보 조건 및 다건 조회 api
    @GetMapping
    public ResponseEntity slcEosDomains(@RequestParam Map<String, String> parameters,
                                                         @RequestHeader(name = "login-id") String loginId,
                                                         @RequestHeader(name = "standard-date", required = false) String standardDate,
                                                         @RequestHeader(name = "gid", required = false) String gid,
                                                         @RequestHeader(name = "seq", defaultValue = "0") int seq){

        if(!slcEosService.validateCheckStandardDate(standardDate)){ standardDate = slcEosService.callPrepostStandardDate(); }
        if(!slcEosService.validateCheckGlobalId(gid)){ gid = slcEosService.callPrePostGid(); }

        // page 와 size 는 web에서 지정해주어야 함.
        Page<SlcEosDto> slcEosDtoPage = slcEosService.getLicEosQuery(0, 5, parameters, loginId, standardDate, gid, seq);

        return slcEosDtoPage != null ? ResponseEntity.ok().body(slcEosDtoPage) :
                ResponseEntity.badRequest().body(new SlcCommonResponse("LIC-E-500", "처리 실패했습니다."));

    }
}
