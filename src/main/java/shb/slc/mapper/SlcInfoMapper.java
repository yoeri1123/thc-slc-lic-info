package shb.slc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import shb.slc.domain.SlcInfoDomain;
import shb.slc.dto.SlcInfoDto;

@Mapper
public interface SlcInfoMapper {
    SlcInfoMapper INSTANCE = Mappers.getMapper(SlcInfoMapper.class);
    SlcInfoDomain dtoToEntity(SlcInfoDto slcInfoDto);
    SlcInfoDto entityToDto(SlcInfoDomain slcInfoDomain);
    SlcInfoDto updDtoToDto(SlcInfoDto slcInfoDto, SlcInfoDto slcInfoUpdDto);
}
