package shb.slc.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import shb.slc.configuration.SlcInfoDtoPredicate;
import shb.slc.dao.SlcInfoDao;
import shb.slc.domain.SlcInfoDomain;
import shb.slc.domain.SlcInfoUpdDomain;
import shb.slc.dto.QSlcInfoDto;
import shb.slc.dto.SlcInfoDto;
import shb.slc.mapper.SlcInfoMapperImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class SlcInfoService {

    @Autowired
    SlcInfoDao slcInfoDao;

    @Value("${prepost.stdate.url}")
    private String stdPrepostUrl;

    @Value("${prepost.gid.url}")
    private String gidPrepostUrl;

    SlcInfoMapperImpl slcInfoMapper;

    Logger logger= LoggerFactory.getLogger(SlcInfoService.class);


    // gid 가 null 이거나 없을 경우 false return
    public Boolean validateCheckStandardDate(String standardDate){ return (standardDate==null || standardDate.isEmpty())? false : true; }
    public Boolean validateCheckGlobalId(String gid){ return (gid == null || gid.isEmpty()) ? false : true; }
    public Boolean validateCheckLicInfoObject(Long id) { return slcInfoDao.existsById(id); }

    public String callPrepostStandardDate(){
        HttpResponse httpResponse = (HttpResponse) Unirest.get(stdPrepostUrl).asString();
        return httpResponse.getBody().toString();
    }

    public String callPrePostGid(){
        HttpResponse httpResponse = (HttpResponse) Unirest.get(gidPrepostUrl).asString();
        return httpResponse.getBody().toString();
    }

    public Boolean addLicInfo(SlcInfoDomain slcInfoDomain,
                           String loginId,
                           String standardDate,
                           String gid,
                           int seq) throws SQLException{
        SlcInfoDto slcInfoDto = slcInfoMapper.INSTANCE.entityToDto(slcInfoDomain);
        slcInfoDto.setRegNm(loginId);
        slcInfoDto.setRegDt(standardDate);
        try {
            slcInfoDao.save(slcInfoDto);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    //update할 때, 객체를 어떤걸 써야 좋을지 고민.. SlcInfoUpdDomain -> SlcInfoDto ?
    // Boolean -> 에러 로직 처리할 수 있게 객체로 하기
    public Boolean editLicInfo(SlcInfoUpdDomain slcInfoUpdDomain,
                               String loginId,
                               String standardDate,
                               String gid,
                               int seq) throws SQLException{

        SlcInfoDto slcInfoDto = slcInfoDao.findByLicInfoNo(slcInfoUpdDomain.getLicInfoNo());

        SlcInfoDomain slcInfoDomain = new SlcInfoDomain(slcInfoUpdDomain);
        SlcInfoDto slcInfoUpdDto=slcInfoMapper.INSTANCE.entityToDto(slcInfoDomain);

        // Update Object
        slcInfoDto = slcInfoMapper.INSTANCE.updDtoToDto(slcInfoDto, slcInfoUpdDto);
        slcInfoDto.setUdtNm(loginId);
        slcInfoDto.setUdtDt(standardDate);
        try {
            slcInfoDao.save(slcInfoDto);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    public Boolean removeLicInfo(Long licInfoNo,
                                 String loginId,
                                 String standardDate,
                                 String gid,
                                 int seq){
        try{
            slcInfoDao.deleteById(licInfoNo);
            return true;
        }catch (Exception e){
            return false;
        }

    }
    // slcInfoDomain 으로 나가는게 날지, 아니면 slcInfoDto 로 나가는게 날지
    public SlcInfoDomain getLicInfoDetail(Long licInfoNo,
                                          String loginId,
                                          String standardDate,
                                          String gid,
                                          int seq){
        try{
            SlcInfoDto slcInfoDto = slcInfoDao.findByLicInfoNo(licInfoNo);
            SlcInfoDomain slcInfoDomain = slcInfoMapper.INSTANCE.dtoToEntity(slcInfoDto);
            return slcInfoDomain;
        }catch (Exception e){
            return null;
        }
    }


    //QueryDsl Use Dynamic Query
    public Page<SlcInfoDto> getLicInfoQuery(int page, int size, Map<String, String> parameters, String loginId, String standardDate, String gid, int seq){

        try{
            Page<SlcInfoDto> result = (Page<SlcInfoDto>) slcInfoDao.findAll(SlcInfoDtoPredicate.search(parameters), PageRequest.of(page, size));
            return result;
        } catch(Throwable e) {

        }
        return null;
    }


}
