package shb.slc.service;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import shb.slc.configuration.SlcEosDtoPredicate;
import shb.slc.dao.SlcEosDao;
import shb.slc.domain.SlcEosDomain;
import shb.slc.domain.SlcInfoDomain;
import shb.slc.dto.SlcEosDto;
import shb.slc.dto.SlcEosPK;
import shb.slc.mapper.SlcEosMapper;
import shb.slc.mapper.SlcEosMapperImpl;

import java.util.Map;

@Service
public class SlcEosService {

    @Autowired
    SlcEosDao slcEosDao;

    @Value("${prepost.stdate.url}")
    private String stdPrepostUrl;

    @Value("${prepost.gid.url}")
    private String gidPrepostUrl;

    SlcEosMapperImpl slcEosMapper;

    Logger logger = LoggerFactory.getLogger(SlcEosService.class);

    // gid 가 null 이거나 없을 경우 false return
    public Boolean validateCheckStandardDate(String standardDate){ return (standardDate==null || standardDate.isEmpty())? false : true; }
    public Boolean validateCheckGlobalId(String gid){ return (gid == null || gid.isEmpty()) ? false : true; }
    public Boolean validateCheckLicEosObject(String licNm, String licVer) { return slcEosDao.existsById(new SlcEosPK(licNm, licVer));}

    public String callPrepostStandardDate(){
        HttpResponse httpResponse = (HttpResponse) Unirest.get(stdPrepostUrl).asString();
        return httpResponse.getBody().toString();
    }

    public String callPrePostGid(){
        HttpResponse httpResponse = (HttpResponse) Unirest.get(gidPrepostUrl).asString();
        return httpResponse.getBody().toString();
    }

    public Boolean addLicEos(SlcEosDomain slcEosDomain,
                           String loginId,
                           String standardDate,
                           String gid,
                           int seq){
        SlcEosDto slcEosDto = slcEosMapper.INSTANCE.entityToDto(slcEosDomain);
        slcEosDto.setRegNm(loginId);
        slcEosDto.setRegDt(standardDate);
        try{
            slcEosDao.save(slcEosDto);
            return true;
        }catch(Exception e){
            return false;
        }

    }
    public Boolean editLicEos(SlcEosDto slcEosDto,
                              String loginId,
                              String standardDate,
                              String gid,
                              int seq){
        slcEosDto.setUdtNm(loginId);
        slcEosDto.setUdtDt(standardDate);
        try{
            slcEosDao.save(slcEosDto);
            return true;
        }catch(Exception e){return false;}
    }
    public Boolean removeLicInfo(String LicNm, String LicVer,
                                 String loginId,
                                 String standardDate,
                                 String gid,
                                 int seq){
        try{
            slcEosDao.deleteById(new SlcEosPK(LicNm, LicVer));
            return true;
        }catch(Exception e){
            return false;
        }
    }
    public SlcEosDomain getEosDetail(String licNm, String licVer,
                                     String loginId,
                                     String standardDate,
                                     String gid,
                                     int seq){
        try{
            SlcEosDto slcEosDto = slcEosDao.findByLicNmAndLicVer(licNm, licVer);
            SlcEosDomain slcEosDomain = slcEosMapper.INSTANCE.dtoToEntity(slcEosDto);
            return slcEosDomain;
        }catch (Exception e){
            return null;
        }
    }

    //QueryDsl Use Dynamic Query
    public Page<SlcEosDto> getLicEosQuery(int page, int size, Map<String, String> parameters,
                                          String loginId,
                                          String standardDate,
                                          String gid,
                                          int seq){
        try{
            Page<SlcEosDto> result = (Page<SlcEosDto>) slcEosDao.findAll(SlcEosDtoPredicate.search(parameters), PageRequest.of(page, size));
            return result;
        }catch(Throwable e){

        }
        return null;
    }
}
