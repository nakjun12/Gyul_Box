package jeju.oneroom.houseInfo.mapper;

import jeju.oneroom.houseInfo.dto.HouseInfoDto;
import jeju.oneroom.houseInfo.entity.HouseInfo;
import jeju.oneroom.review.mapper.ReviewMapper;
import jeju.oneroom.user.mapper.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {ReviewMapper.class, UserMapper.class})
public interface HouseInfoMapper {
    @Mapping(target = "floor", expression = "java(\"지상 \" +  houseInfo.getGrndFloor() + \"층 (지하 \" + houseInfo.getUgrndFloor() + \"층)\")")
    HouseInfoDto.Response houseInfoToResponseDto(HouseInfo houseInfo);

    HouseInfoDto.SimpleResponse houseInfoToSimpleResponseDto(HouseInfo houseInfo);

    HouseInfoDto.SimpleCountResponse houseInfoToSimpleCountResponseDto(HouseInfo houseInfo);

    HouseInfoDto.SimpleContentResponse houseInfoToSimpleContentResponseDto(HouseInfo houseInfo);
}
