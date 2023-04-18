package jeju.oneroom.si.mapper;

import jeju.oneroom.si.dto.SiDto;
import jeju.oneroom.si.entity.Si;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SiMapper {
    SiDto.Response siToResponseDto(Si si);
}
