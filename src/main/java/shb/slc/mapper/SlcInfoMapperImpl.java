package shb.slc.mapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import shb.slc.domain.SlcInfoDomain;
import shb.slc.dto.SlcInfoDto;

@NoArgsConstructor
public class SlcInfoMapperImpl implements SlcInfoMapper{
    @Override
    public SlcInfoDomain dtoToEntity(SlcInfoDto slcInfoDto) {
        if( slcInfoDto == null ){ return null; }

        SlcInfoDomain.SlcInfoDomainBuilder slcInfoDomain = SlcInfoDomain.builder();

        slcInfoDomain.licNm( slcInfoDto.getLicNm() );
        slcInfoDomain.licDtl( slcInfoDto.getLicDtl() );
        slcInfoDomain.licMangNm( slcInfoDto.getLicMangNm() );
        slcInfoDomain.licDept( slcInfoDto.getLicDept() );
        slcInfoDomain.licMfact( slcInfoDto.getLicMfact() );
        slcInfoDomain.licMaint( slcInfoDto.getLicMaint() );
        slcInfoDomain.mfactNm( slcInfoDto.getMfactNm() );

        return slcInfoDomain.build();
    }

    @Override
    public SlcInfoDto entityToDto(SlcInfoDomain slcInfoDomain) {
        if ( slcInfoDomain == null ){ return null; }

        SlcInfoDto.SlcInfoDtoBuilder slcInfoDto = SlcInfoDto.builder();

        slcInfoDto.licNm( slcInfoDomain.getLicNm() );
        slcInfoDto.licDtl( slcInfoDomain.getLicDtl() );
        slcInfoDto.licMangNm( slcInfoDomain.getLicMangNm() );
        slcInfoDto.licDept( slcInfoDomain.getLicDept() );
        slcInfoDto.licMfact( slcInfoDomain.getLicMfact() );
        slcInfoDto.licMaint( slcInfoDomain.getLicMaint() );
        slcInfoDto.mfactNm( slcInfoDomain.getMfactNm() );
        slcInfoDto.maintNm( slcInfoDomain.getMaintNm() );

        return slcInfoDto.build();
    }

    @Override
    public SlcInfoDto updDtoToDto(SlcInfoDto slcInfoDto, SlcInfoDto slcInfoUpdDto) {
        if( slcInfoDto == null || slcInfoUpdDto == null ) { return null; }

        slcInfoDto.setLicNm(slcInfoUpdDto.getLicNm());
        slcInfoDto.setLicDtl(slcInfoUpdDto.getLicDtl());
        slcInfoDto.setLicMangNm(slcInfoUpdDto.getLicMangNm());
        slcInfoDto.setLicDept(slcInfoUpdDto.getLicDept());
        slcInfoDto.setLicMfact(slcInfoUpdDto.getLicMfact());
        slcInfoDto.setLicMaint(slcInfoUpdDto.getLicMaint());
        slcInfoDto.setMfactNm(slcInfoUpdDto.getMfactNm());
        slcInfoDto.setMaintNm(slcInfoUpdDto.getMaintNm());

        return slcInfoDto;
    }

}
