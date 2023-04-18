package jeju.oneroom.houseInfo.mapper;

import jeju.oneroom.houseInfo.dto.HouseInfoDto;
import jeju.oneroom.houseInfo.entity.HouseInfo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HouseInfoMapper {
    HouseInfoDto.Response houseInfoToResponseDto(HouseInfo houseInfo);
}
