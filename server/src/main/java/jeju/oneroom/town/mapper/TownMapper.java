package jeju.oneroom.town.mapper;

import jeju.oneroom.town.dto.TownDto;
import jeju.oneroom.town.entity.Town;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TownMapper {
    TownDto.Response townToResponseDto(Town town);
}
