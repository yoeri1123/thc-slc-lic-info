package shb.slc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shb.slc.dao.SlcRegDao;

@Service
public class SlcRegService {

    @Autowired
    SlcRegDao slcRegDao;

    // gid 가 null 이거나 없을 경우 false return
    public Boolean validateCheckStandardDate(String standardDate){ return (standardDate==null || standardDate.isEmpty())? false : true; }
    public Boolean validateCheckGlobalId(String gid){ return (gid == null || gid.isEmpty()) ? false : true; }
}
