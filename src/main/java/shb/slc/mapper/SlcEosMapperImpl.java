package shb.slc.mapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import shb.slc.domain.SlcEosDomain;
import shb.slc.dto.SlcEosDto;

@NoArgsConstructor
public class SlcEosMapperImpl implements SlcEosMapper{

    @Override
    public SlcEosDomain dtoToEntity(SlcEosDto slcEosDto) {

        if( slcEosDto == null ){ return null; }

        SlcEosDomain.SlcEosDomainBuilder slcEosDomain = SlcEosDomain.builder();

        slcEosDomain.licNm( slcEosDto.getLicNm() );
        slcEosDomain.licVer( slcEosDto.getLicVer() );
        slcEosDomain.licPatchVer( slcEosDto.getLicPatchVer() );
        slcEosDomain.licEos( slcEosDto.getLicEos() );
        slcEosDomain.licEosl( slcEosDto.getLicEosl() );
        slcEosDomain.licEos( slcEosDto.getLicEos() );

        return slcEosDomain.build();
    }

    @Override
    public SlcEosDto entityToDto(SlcEosDomain slcEosDomain) {

        if( slcEosDomain == null ){ return null; }

        SlcEosDto.SlcEosDtoBuilder slcEosDto = SlcEosDto.builder();

        slcEosDto.licNm( slcEosDomain.getLicNm() );
        slcEosDto.licVer( slcEosDomain.getLicVer() );
        slcEosDto.licPatchVer( slcEosDomain.getLicPatchVer() );
        slcEosDto.licEos( slcEosDomain.getLicEos() );
        slcEosDto.licEosl( slcEosDomain.getLicEosl() );
        slcEosDto.licEol( slcEosDomain.getLicEol() );

        return slcEosDto.build();
    }
}
