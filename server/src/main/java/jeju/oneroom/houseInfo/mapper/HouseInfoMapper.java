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
    // grndFloor, ugrndFloor 변수를 dto의 floor 변수로 통합
    @Mapping(target = "floor", expression = "java(\"지상 \" +  houseInfo.getGrndFloor() + \"층 (지하 \" + houseInfo.getUgrndFloor() + \"층)\")")
    // houseName이 없을 경우 platPlc으로 대체
    @Mapping(target = "houseName", defaultExpression = "java(houseInfo.getPlatPlc())")
    HouseInfoDto.Response houseInfoToResponseDto(HouseInfo houseInfo);

    @Mapping(target = "houseName", defaultExpression = "java(houseInfo.getPlatPlc())")
    HouseInfoDto.SimpleResponse houseInfoToSimpleResponseDto(HouseInfo houseInfo);

    HouseInfoDto.SimpleCountResponse houseInfoToSimpleCountResponseDto(HouseInfo houseInfo);

    @Mapping(target = "houseName", defaultExpression = "java(houseInfo.getPlatPlc())")
    HouseInfoDto.SimpleContentResponse houseInfoToSimpleContentResponseDto(HouseInfo houseInfo);
}
