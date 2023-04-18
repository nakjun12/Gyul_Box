package jeju.oneroom.review.controller;

import jeju.oneroom.common.dto.MultiResponseDto;
import jeju.oneroom.review.dto.ReviewDto;
import jeju.oneroom.review.entity.Review;
import jeju.oneroom.review.mapper.ReviewMapper;
import jeju.oneroom.review.repository.ReviewRepository;
import jeju.oneroom.user.entity.User;
import jeju.oneroom.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final UserRepository userRepository;

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
        User user = userRepository.findById(userId).orElse(null);
        long userTownCode = user.getTown().getTownCode();
        // 추후 작성
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 유저 리뷰 찾기  추후 findUserTownReviews 메서드와 통합 시도.
    @GetMapping("users/{user-id}/reviews")
    public ResponseEntity<?> findUserReviews(@RequestParam int page,
                                             @RequestParam int size,
                                             @PathVariable("user-id") long userId) {
        Page<ReviewDto.SimpleResponse> responses = reviewRepository.findByUser_Id(userId, PageRequest.of(page - 1, size)).map(reviewMapper::reviewToSimpleResponseDto);

        return new ResponseEntity<>(new MultiResponseDto<>(responses), HttpStatus.OK);
    }

    // Town에 따른 거주 리뷰 아마 30개?
    @GetMapping("towns/{town-id}/reviews")
    public ResponseEntity<?> findTownReviews(@RequestParam int page,
                                             @RequestParam int size,
                                             @PathVariable("town-id") long townCode) {
        Page<ReviewDto.SimpleResponse> responses = reviewRepository.findByHouseInfo_Town_TownCode(townCode, PageRequest.of(page - 1, size)).map(reviewMapper::reviewToSimpleResponseDto);
        return new ResponseEntity<>(new MultiResponseDto<>(responses), HttpStatus.OK);
    }
}
