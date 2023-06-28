package jeju.oneroom.review.mapper;

import jeju.oneroom.houseInfo.entity.HouseInfo;
import jeju.oneroom.review.dto.ReviewDto;
import jeju.oneroom.review.entity.Review;
import jeju.oneroom.user.mapper.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {UserMapper.class})
public interface ReviewMapper {
    Review postDtoToReview(ReviewDto.Post postDto);

    Review patchDtoToReview(ReviewDto.Patch patchDto);

    //리뷰 작성자의 정보 - UserMapper를 차용하여 매핑
    @Mapping(target = "writer", source = "user")
    @Mapping(target = "avgRate", expression = "java(review.getRate().getAvgRate())")
    ReviewDto.Response reviewToResponseDto(Review review);

    ReviewDto.SimpleResponse reviewToSimpleResponseDto(Review review);

    default long toLong(HouseInfo houseInfo) {
        return houseInfo.getId();
    }
}
