package shb.slc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import shb.slc.domain.SlcRegDomain;
import shb.slc.domain.SlcRegOutDomain;
import shb.slc.domain.SlcRegUpdDomain;
import shb.slc.dto.SlcInfoDto;
import shb.slc.dto.SlcRegDto;

@Mapper
public interface SlcRegMapper {
    SlcRegMapper INSTANCE= Mappers.getMapper(SlcRegMapper.class);
    SlcRegDomain dtoToEntity(SlcRegDto slcRegDto);
    SlcRegOutDomain dtoToOutEntity(SlcRegDto slcRegDto);
    SlcRegDto entityToDto(SlcRegDomain slcRegDomain);
    SlcRegDto updDtoToDto(SlcRegDto slcRegDto, SlcRegUpdDomain slcRegUpdDomain);
}
