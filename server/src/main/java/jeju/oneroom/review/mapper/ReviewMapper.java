package jeju.oneroom.review.mapper;

import jeju.oneroom.review.dto.ReviewDto;
import jeju.oneroom.review.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReviewMapper {
    Review postDtoToReview(ReviewDto.Post postDto);
    Review patchDtoToReview(ReviewDto.Patch patchDto);
    ReviewDto.Response reviewToResponseDto(Review review);
    ReviewDto.SimpleResponse reviewTpSimpleResponseDto(Review review);
}
