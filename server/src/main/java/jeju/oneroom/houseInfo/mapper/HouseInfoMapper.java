package jeju.oneroom.houseInfo.mapper;

import jeju.oneroom.houseInfo.dto.HouseInfoDto;
import jeju.oneroom.houseInfo.entity.HouseInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HouseInfoMapper {
    @Mapping(target = "floor", expression = "java(\"지상 \" +  houseInfo.getGrndFloor() + \"층 (지하 \" + houseInfo.getUgrndFloor() + \"층)\")")
    HouseInfoDto.Response houseInfoToResponseDto(HouseInfo houseInfo);
    HouseInfoDto.SimpleResponse houseInfoToSimpleResponseDto(HouseInfo houseInfo);
    HouseInfoDto.SimpleCountResponse houseInfoToSimpleCountResponseDto(HouseInfo houseInfo);
}
