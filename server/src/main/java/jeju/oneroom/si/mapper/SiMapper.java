package jeju.oneroom.si.mapper;

import jeju.oneroom.si.dto.SiDto;
import jeju.oneroom.si.entity.Si;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SiMapper {
    SiDto.Response siToResponseDto(Si si);
}
