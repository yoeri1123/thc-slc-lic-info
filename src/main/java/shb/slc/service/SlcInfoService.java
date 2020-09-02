package shb.slc.service;

import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import shb.slc.dao.SlcInfoDao;
import shb.slc.domain.SlcEosDomain;
import shb.slc.domain.SlcInfoDomain;
import shb.slc.domain.SlcInfoUpdDomain;
import shb.slc.dto.SlcInfoDto;
import shb.slc.mapper.SlcInfoMapper;
import shb.slc.mapper.SlcInfoMapperImpl;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class SlcInfoService {

    @Autowired
    SlcInfoDao slcInfoDao;

    EntityManager entityManager;

    SlcInfoMapperImpl slcInfoMapper;

    // gid 가 null 이거나 없을 경우 false return
    public Boolean validateCheckStandardDate(String standardDate){ return (standardDate==null || standardDate.isEmpty())? false : true; }
    public Boolean validateCheckGlobalId(String gid){ return (gid == null || gid.isEmpty()) ? false : true; }
    public Boolean validateCheckLicInfoObject(Long id) { return slcInfoDao.existsById(id); }

    public Boolean addLicInfo(SlcInfoDomain slcInfoDomain,
                           String loginId,
                           String standardDate,
                           String gid,
                           int seq) throws SQLException{
        log.info(slcInfoDomain.toString());
        SlcInfoDto slcInfoDto = slcInfoMapper.INSTANCE.entityToDto(slcInfoDomain);
        log.info(slcInfoDto.toString());
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
    // web에서 데이터를 다 떤져준다고 생각하고 dbio 줄이기
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

    public List<SlcInfoDto> getLicInfo(int page, int size, Map<String, String> parameters, String loginId, String standardDate, String gid, int seq){
        return null;
    }
    public Page<SlcInfoDto> getLicInfoAll(int page, int size){
        //PageRequest
        return slcInfoDao.findAll(PageRequest.of(page,size));

    }
    // 조건으로 찾을 경우에는 보통 어떻게 쿼리 처리를 하는지? 단일일 경우에는 관계 없지만 겹쳐서 사용하는 쿼리일 경우.. 궁금
    public List<SlcInfoDto> getLicInfoQuery(int page, int size, Map<String, String> parameters, String loginId, String standardDate, String gid, int seq){

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);

        return jpaQueryFactory.selectFrom()

        List<SlcInfoDto> slcInfoDtos = new ArrayList<>();

        for(String key : parameters.keySet() ){
            switch (key){
                case "licNm":
                    slcInfoDtos.add(slcInfoDao.findByLicNm(parameters.get(key)));
                    return slcInfoDtos;
                default :
                    break;
            }
        }
        return null;
    }


}
