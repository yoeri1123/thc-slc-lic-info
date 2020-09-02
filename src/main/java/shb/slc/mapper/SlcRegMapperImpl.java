package shb.slc.mapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import shb.slc.domain.SlcRegDomain;
import shb.slc.domain.SlcRegOutDomain;
import shb.slc.domain.SlcRegUpdDomain;
import shb.slc.dto.SlcRegDto;

@NoArgsConstructor
public class SlcRegMapperImpl implements SlcRegMapper{
    @Override
    public SlcRegDomain dtoToEntity(SlcRegDto slcRegDto) {
        if( slcRegDto == null ){ return null; }

        SlcRegDomain.SlcRegDomainBuilder slcRegDomain = SlcRegDomain.builder();

        slcRegDomain.purCntrctNo( slcRegDto.getPurCntrctNo() );
        slcRegDomain.purCntrctDt( slcRegDto.getPurCntrctDt() );
        slcRegDomain.licNm( slcRegDto.getLicNm() );
        slcRegDomain.licVer( slcRegDto.getLicVer() );
        slcRegDomain.typeNo( slcRegDto.getTypeNo() );
        slcRegDomain.purUnit( slcRegDto.getPurUnit() );
        slcRegDomain.purCntrctCnt( slcRegDto.getPurCntrctCnt() );
        slcRegDomain.validBgndt( slcRegDto.getValidBgndt() );
        slcRegDomain.validEnddt( slcRegDto.getValidEnddt() );

        return slcRegDomain.build();
    }

    @Override
    public SlcRegOutDomain dtoToOutEntity(SlcRegDto slcRegDto) {

        if(slcRegDto == null ){ return null; }

        SlcRegOutDomain.SlcRegOutDomainBuilder slcRegOutDomain = SlcRegOutDomain.builder();

        slcRegOutDomain.licNm(slcRegDto.getLicNm());
        slcRegOutDomain.licVer(slcRegDto.getLicVer());
        slcRegOutDomain.purCntrctCnt(slcRegDto.getPurCntrctCnt());

        return slcRegOutDomain.build();
    }

    @Override
    public SlcRegDto entityToDto(SlcRegDomain slcRegDomain) {
        if( slcRegDomain == null ){ return null; }

        SlcRegDto.SlcRegDtoBuilder slcRegDto = SlcRegDto.builder();

        slcRegDto.purCntrctNo( slcRegDomain.getPurCntrctNo() );
        slcRegDto.purCntrctDt( slcRegDomain.getPurCntrctDt() );
        slcRegDto.licNm( slcRegDomain.getLicNm() );
        slcRegDto.licVer( slcRegDomain.getLicVer() );
        slcRegDto.typeNo( slcRegDomain.getTypeNo() );
        slcRegDto.purUnit( slcRegDomain.getPurUnit() );
        slcRegDto.purCntrctCnt( slcRegDomain.getPurCntrctCnt() );
        slcRegDto.validBgndt( slcRegDomain.getValidBgndt() );
        slcRegDto.validEnddt( slcRegDomain.getValidEnddt() );

        return slcRegDto.build();
    }

    @Override
    public SlcRegDto updDtoToDto(SlcRegDto slcRegDto, SlcRegUpdDomain slcRegUpdDomain) {

        if(slcRegDto == null || slcRegUpdDomain==null){ return null; }

        slcRegDto.setPurCntrctNo(slcRegUpdDomain.getPurCntrctNo());
        slcRegDto.setPurCntrctDt(slcRegUpdDomain.getPurCntrctDt());
        slcRegDto.setLicNm(slcRegUpdDomain.getLicNm());
        slcRegDto.setLicVer(slcRegUpdDomain.getLicVer());
        slcRegDto.setTypeNo(slcRegUpdDomain.getTypeNo());
        slcRegDto.setPurUnit(slcRegUpdDomain.getPurUnit());
        slcRegDto.setPurCntrctCnt(slcRegUpdDomain.getPurCntrctCnt());
        slcRegDto.setValidBgndt(slcRegUpdDomain.getValidBgndt());
        slcRegDto.setValidEnddt(slcRegUpdDomain.getValidEnddt());

        return slcRegDto;
    }
}
