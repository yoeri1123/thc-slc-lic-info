package shb.slc.service;

import kong.unirest.Unirest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import shb.slc.configuration.SlcRegDtoPredicate;
import shb.slc.dao.SlcRegDao;
import shb.slc.domain.SlcRegDomain;
import shb.slc.domain.SlcRegOutDomain;
import shb.slc.domain.SlcRegUpdDomain;
import shb.slc.dto.SlcRegDto;
import shb.slc.mapper.SlcRegMapper;
import shb.slc.mapper.SlcRegMapperImpl;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SlcRegService {

    @Autowired
    SlcRegDao slcRegDao;

    @Value("${swuse.url}")
    private String swuseUrl;

    @Value("${prepost.stdate.url}")
    private String stdPrepostUrl;

    @Value("${prepost.gid.url}")
    private String gidPrepostUrl;

    SlcRegMapperImpl slcRegMapper;

    Logger logger= LoggerFactory.getLogger(SlcInfoService.class);

    // gid 가 null 이거나 없을 경우 false return
    public Boolean validateCheckStandardDate(String standardDate){ return (standardDate==null || standardDate.isEmpty())? false : true; }
    public Boolean validateCheckGlobalId(String gid){ return (gid == null || gid.isEmpty()) ? false : true; }
    public Boolean validateCheckLicRegObject(Long id){ return slcRegDao.existsById(id); }

    public String callPrepostStandardDate(){
        kong.unirest.HttpResponse httpResponse = (kong.unirest.HttpResponse) Unirest.get(stdPrepostUrl).asString();
        return httpResponse.getBody().toString();
    }

    public String callPrePostGid(){
        kong.unirest.HttpResponse httpResponse = (kong.unirest.HttpResponse) Unirest.get(gidPrepostUrl).asString();
        return httpResponse.getBody().toString();
    }


    public Boolean addLicReg(SlcRegDomain slcRegDomain, String loginId, String standardDate, String gid, int seq) {
        SlcRegDto slcRegDto = slcRegMapper.INSTANCE.entityToDto(slcRegDomain);
        slcRegDto.setRegNm(loginId);
        slcRegDto.setRegDt(standardDate);

        SlcRegOutDomain slcRegOutDomain = slcRegMapper.INSTANCE.dtoToOutEntity(slcRegDto);

        try{
            slcRegDao.save(slcRegDto);

            HttpResponse httpResponse = (HttpResponse) Unirest.post(swuseUrl)
                    .header("loginId", loginId)
                    .header("standardDate", standardDate)
                    .header("gid", gid)
                    .header("seq", String.valueOf(seq))
                    .body(slcRegOutDomain);

            return true;
        }catch (Exception e) {
            return false;
        }
    }

    public Boolean editLicReg(SlcRegUpdDomain slcRegUpdDomain,
                              String loginId,
                              String standardDate,
                              String gid,
                              int seq){

        SlcRegDto slcRegDto = slcRegDao.findBySlcRegNo(slcRegUpdDomain.getSlcRegNo());
        slcRegDto = slcRegMapper.INSTANCE.updDtoToDto(slcRegDto, slcRegUpdDomain);
        slcRegDto.setUdtNm(loginId);
        slcRegDto.setUdtDt(standardDate);
        try{
            slcRegDao.save(slcRegDto);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public Boolean removeLicReg(Long slcRegNo,
                                String loginId,
                                String standardDate,
                                String gid,
                                int seq){
        try{
            slcRegDao.deleteById(slcRegNo);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public SlcRegDomain getLicRegDetail(Long slcRegNo,
                                        String loginId,
                                        String standardDate,
                                        String gid,
                                        int seq){
        try{
            SlcRegDto slcRegDto = slcRegDao.findBySlcRegNo(slcRegNo);
            SlcRegDomain slcRegDomain = slcRegMapper.INSTANCE.dtoToEntity(slcRegDto);
            return slcRegDomain;
        }catch(Exception e){
            return null;
        }
    }


    public Page<SlcRegDto> getLicRegQuery(int page, int size, Map<String, String> parameters, String loginId, String standardDate, String gid, int seq){
        try{
            Page<SlcRegDto> result = (Page<SlcRegDto>) slcRegDao.findAll(SlcRegDtoPredicate.search(parameters), PageRequest.of(page, size));
            return result;
        }catch (Throwable e){

        }
        return null;
    }

}
