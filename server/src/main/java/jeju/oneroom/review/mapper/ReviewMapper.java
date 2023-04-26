package jeju.oneroom.review.mapper;

import jeju.oneroom.area.entity.Area;
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

    @Mapping(target = "writer", source = "user")
    @Mapping(target = "avgRate", expression = "java(review.getRate().getAvgRate())")
    ReviewDto.Response reviewToResponseDto(Review review);

    ReviewDto.SimpleResponse reviewToSimpleResponseDto(Review review);

    default String toString(Area area) {
        return area.getAreaName();
    }
}
