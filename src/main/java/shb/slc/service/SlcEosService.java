package shb.slc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shb.slc.dao.SlcEosDao;
import shb.slc.domain.SlcEosDomain;
import shb.slc.domain.SlcInfoDomain;
import shb.slc.mapper.SlcEosMapper;
import shb.slc.mapper.SlcEosMapperImpl;

@Service
public class SlcEosService {

    @Autowired
    SlcEosDao slcEosDao;

    SlcEosMapperImpl slcEosMapper;

    // gid 가 null 이거나 없을 경우 false return
    public Boolean validateCheckStandardDate(String standardDate){ return (standardDate==null || standardDate.isEmpty())? false : true; }
    public Boolean validateCheckGlobalId(String gid){ return (gid == null || gid.isEmpty()) ? false : true; }
    public Boolean validateCheckLicEosObject(String licNm, String licVer) { return slcEosDao.existsById()}

    public void addLicInfo(SlcEosDomain slcEosDomain,
                           String loginId,
                           String standardDate,
                           String gid,
                           int seq){


    }
}
