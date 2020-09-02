package shb.slc.service;

import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
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

    SlcRegMapperImpl slcRegMapper;

    // gid 가 null 이거나 없을 경우 false return
    public Boolean validateCheckStandardDate(String standardDate){ return (standardDate==null || standardDate.isEmpty())? false : true; }
    public Boolean validateCheckGlobalId(String gid){ return (gid == null || gid.isEmpty()) ? false : true; }
    public Boolean validateCheckLicRegObject(Long id){ return slcRegDao.existsById(id); }

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

    public Page<SlcRegDto> getLicRegAll(int page, int size){
        //PageRequest
        return slcRegDao.findAll(PageRequest.of(page, size));
    }

    public List<SlcRegDto> getLicRegQuery(Map<String, String> parameters, String loginId, String standardDate, String gid, int seq){
        List<SlcRegDto> slcRegDtos = new ArrayList<>();

        for(String key : parameters.keySet()){
            switch (key){
                case "purCntrctNo":
                    slcRegDtos.add(slcRegDao.findByPurCntrctNo(parameters.get(key)));
                    return slcRegDtos;
                default:
                    break;
            }
        }
        return null;
    }

}
