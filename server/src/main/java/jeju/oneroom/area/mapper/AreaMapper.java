package jeju.oneroom.area.mapper;

import jeju.oneroom.area.dto.AreaDto;
import jeju.oneroom.area.entity.Area;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AreaMapper {
    AreaDto.Response areaToResponseDto(Area area);
}
