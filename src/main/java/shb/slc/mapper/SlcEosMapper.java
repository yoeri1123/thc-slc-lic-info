package shb.slc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import shb.slc.domain.SlcEosDomain;
import shb.slc.dto.SlcEosDto;

@Mapper
public interface SlcEosMapper {
    SlcEosMapper INSTANCE = Mappers.getMapper(SlcEosMapper.class);
    SlcEosDomain dtoToEntity(SlcEosDto slcEosDto);
    SlcEosDto entityToDto(SlcEosDomain slcEosDomain);

}

