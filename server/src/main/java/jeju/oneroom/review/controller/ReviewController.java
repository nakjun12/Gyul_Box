package jeju.oneroom.review.controller;

import jeju.oneroom.houseInfo.dto.HouseInfoDto;
import jeju.oneroom.houseInfo.entity.HouseInfo;
import jeju.oneroom.review.dto.ReviewDto;
import jeju.oneroom.review.entity.Review;
import jeju.oneroom.review.mapper.ReviewMapper;
import jeju.oneroom.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @PostMapping("/reviews")
    public ResponseEntity<?> post() {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/reviews/{review-id}")
    public ResponseEntity<?> patch(@PathVariable("review-id") long reviewId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/reviews/{review-id}")
    public ResponseEntity<?> delete(@PathVariable("review-id") long reviewId) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/reviews/likes/{review-id}")
    public ResponseEntity<?> putLikes(@PathVariable("review-id") @Positive long reviewId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //  리뷰 단일 조회
    @GetMapping("/reviews/{review-id}")
    public ResponseEntity<?> findReview(@PathVariable("review-id") long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        ReviewDto.Response response = reviewMapper.reviewToResponseDto(review);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 유저 관심 지역 추천 순 리뷰 5개
    @GetMapping("users/{user-id}/user-towns/reviews")
    public ResponseEntity<?> findUserTownReviews(@PathVariable("user-id") long userId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 유저 리뷰 찾기  추후 findUserTownReviews메서드와 통합 시도.
    @GetMapping("users/{user-id}/reviews")
    public ResponseEntity<?> findUserReviews(@PathVariable("user-id") long userId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Town에 따른 거주 리뷰 아마 30개?
    @GetMapping("towns/{town-id}/reviews")
    public ResponseEntity<?> findTownReviews(@PathVariable("town-id") long townCode) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
