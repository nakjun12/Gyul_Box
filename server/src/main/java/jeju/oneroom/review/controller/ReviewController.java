package jeju.oneroom.review.controller;

import jeju.oneroom.area.entity.Area;
import jeju.oneroom.area.repository.AreaRepository;
import jeju.oneroom.common.dto.ListResponseDto;
import jeju.oneroom.common.dto.MultiResponseDto;
import jeju.oneroom.review.dto.ReviewDto;
import jeju.oneroom.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/reviews")
    public ResponseEntity<?> post(@Valid @RequestBody ReviewDto.Post postDto) {
        reviewService.createReview(postDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/reviews/{review-id}")
    public ResponseEntity<?> patch(@PathVariable("review-id") long reviewId,
                                   @Valid @RequestBody ReviewDto.Patch patchDto) {
        reviewService.updateReview(patchDto, reviewId);

//        ReviewDto.Patch patchDto2 = ReviewDto.Patch.builder()
//                .reviewId(reviewId)
//                .advantage(patchDto.getAdvantage())
//                .disadvantage(patchDto.getDisadvantage())
//                .adminCost(patchDto.getAdminCost())
//                .residenceYear(patchDto.getResidenceYear())
//                .floor(patchDto.getFloor())
//                .rate(patchDto.getRate())
//                .build();

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/reviews/{review-id}")
    public ResponseEntity<?> delete(@PathVariable("review-id") long reviewId) {
        reviewService.deleteReview(reviewId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/reviews/likes/{review-id}/{user-id}")
    public ResponseEntity<?> putLikes(@PathVariable("review-id") @Positive long reviewId,
                                      @PathVariable("user-id") @Positive long userId) {
        reviewService.likeReview(reviewId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //  리뷰 단일 조회
    @GetMapping("/reviews/{review-id}")
    public ResponseEntity<?> getReview(@PathVariable("review-id") long reviewId) {
        ReviewDto.Response findReview = reviewService.findReview(reviewId);
        return new ResponseEntity<>(findReview, HttpStatus.OK);
    }

    @GetMapping("/reviews/latest5") // 최신순 5개
    public ResponseEntity<?> getTop5LatestReviews() {
        List<ReviewDto.SimpleResponse> top5LatestReviews = reviewService.findTop5LatestReviews();
        return new ResponseEntity<>(new ListResponseDto<>(top5LatestReviews), HttpStatus.OK);
    }

    @GetMapping("/reviews/hottest5") // 추천순 5개
    public ResponseEntity<?> getTop5HottestReviews() {
        List<ReviewDto.SimpleResponse> top5HottestReviews = reviewService.findTop5HottestReviews();
        return new ResponseEntity<>(new ListResponseDto<>(top5HottestReviews), HttpStatus.OK);
    }

    // 유저 관심 지역 추천 순 리뷰 5개
    @GetMapping("users/{user-id}/user-areas/reviews")
    public ResponseEntity<?> getUserAreaReviews(@PathVariable("user-id") long userId) {
        List<ReviewDto.SimpleResponse> userAreaReviews = reviewService.findUserAreaReviews(userId);
        return new ResponseEntity<>(new ListResponseDto<>(userAreaReviews), HttpStatus.OK);
    }

    // 유저 리뷰 찾기  추후 findUserTownReviews 메서드와 통합 시도.
    @GetMapping("users/{user-id}/reviews")
    public ResponseEntity<?> getUserReviews(@RequestParam int page,
                                            @RequestParam int size,
                                            @PathVariable("user-id") long userId) {
        Page<ReviewDto.SimpleResponse> userReviews = reviewService.findUserReviews(userId, page, size);
        return new ResponseEntity<>(new MultiResponseDto<>(userReviews), HttpStatus.OK);
    }

    // Town에 따른 거주 리뷰 아마 30개?
    @GetMapping("areas/{area-id}/reviews")
    public ResponseEntity<?> getAreaReviews(@RequestParam int page,
                                            @RequestParam int size,
                                            @PathVariable("area-id") long areaCode) {
        List<ReviewDto.SimpleResponse> areaReviews = reviewService.findAreaReviews(areaCode, page, size);
        return new ResponseEntity<>(new ListResponseDto<>(areaReviews), HttpStatus.OK);
    }
}
