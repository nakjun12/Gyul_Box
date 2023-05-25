package jeju.oneroom.review.controller;

import jeju.oneroom.area.entity.Area;
import jeju.oneroom.area.service.AreaService;
import jeju.oneroom.common.dto.ListResponseDto;
import jeju.oneroom.common.dto.MultiResponseDto;
import jeju.oneroom.houseInfo.entity.HouseInfo;
import jeju.oneroom.houseInfo.service.HouseInfoService;
import jeju.oneroom.review.dto.ReviewDto;
import jeju.oneroom.review.entity.Review;
import jeju.oneroom.review.service.ReviewService;
import jeju.oneroom.user.entity.User;
import jeju.oneroom.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final UserService userService;
    private final AreaService areaService;
    private final HouseInfoService houseInfoService;

    @PostMapping("/reviews")
    public ResponseEntity<?> post(@Valid @RequestBody ReviewDto.Post postDto) {
        HouseInfo houseInfo = houseInfoService.findVerifiedHouseInfo(postDto.getHouseInfoId());
        User user = userService.verifyExistsUserByEmail(postDto.getUserEmail());
        Review review = reviewService.createReview(postDto, houseInfo, user);
        houseInfoService.updateHouseInfoRate(houseInfo,postDto.getRate());
        return new ResponseEntity<>(review.getId(), HttpStatus.CREATED);
        // 원룸 유형 받아주는 것 추가
    }

    @PatchMapping("/reviews/{review-id}")
    public ResponseEntity<?> patch(@Valid @RequestBody ReviewDto.Patch patchDto,
                                   @PathVariable("review-id") long reviewId) {
        patchDto.setReviewId(reviewId);
        Review review = reviewService.updateReview(patchDto);
        return new ResponseEntity<>(review.getId(), HttpStatus.OK);
    }

    @DeleteMapping("/reviews/{review-id}")
    public ResponseEntity<?> delete(@PathVariable("review-id") long reviewId) {
        reviewService.deleteReview(reviewId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //  리뷰 단일 조회
    @GetMapping("/reviews/{review-id}")
    public ResponseEntity<?> getReview(@PathVariable("review-id") long reviewId) {
        ReviewDto.Response findReview = reviewService.findReview(reviewId);
        return new ResponseEntity<>(findReview, HttpStatus.OK);
    }

    @GetMapping("/reviews/hottest2") // 추천순 2개
    public ResponseEntity<?> getTop2HottestReviews() {
        List<ReviewDto.SimpleResponse> top2HottestReviews = reviewService.findTop2HottestReviews();
        return new ResponseEntity<>(new ListResponseDto<>(top2HottestReviews), HttpStatus.OK);
    }

    // 유저 관심 지역 추천 순 리뷰 5개
    @GetMapping("users/{user-id}/user-areas/reviews")
    public ResponseEntity<?> getTop5UserAreaReviews(@PathVariable("user-id") long userId) {
        User user = userService.verifyExistsUser(userId);
        List<ReviewDto.SimpleResponse> userAreaReviews = reviewService.findTop5UserAreaReviews(user);
        return new ResponseEntity<>(new ListResponseDto<>(userAreaReviews), HttpStatus.OK);
    }

    // 유저 리뷰 찾기
    @GetMapping("users/{user-id}/reviews")
    public ResponseEntity<?> getUserReviews(@RequestParam int page,
                                            @RequestParam int size,
                                            @PathVariable("user-id") long userId) {
        User user = userService.verifyExistsUser(userId);
        Page<ReviewDto.SimpleResponse> userReviews = reviewService.findUserReviews(user, page, size);
        return new ResponseEntity<>(new MultiResponseDto<>(userReviews), HttpStatus.OK);
    }

    // Area에 따른 거주 리뷰 아마 30개?
    //api 문서 추가 page
    @GetMapping("areas/{area-id}/reviews")
    public ResponseEntity<?> getAreaReviews(@RequestParam int page,
                                            @RequestParam int size,
                                            @PathVariable("area-id") long areaCode) {
        Area area = areaService.findVerifiedAreaByAreaCode(areaCode);
        Page<ReviewDto.SimpleResponse> areaReviews = reviewService.findAreaReviews(area, page, size);
        return new ResponseEntity<>(new MultiResponseDto<>(areaReviews), HttpStatus.OK);
    }

    // houseInfo에 따른 Review Slice로 for 무한스크롤
    @GetMapping("houseInfos/{houseInfo-id}/reviews")
    public ResponseEntity<?> getHouseInfoReviews(@RequestParam int page,
                                            @RequestParam int size,
                                            @PathVariable("houseInfo-id") long houseInfoId) {
        HouseInfo houseInfo = houseInfoService.findVerifiedHouseInfo(houseInfoId);
        Page<ReviewDto.Response> houseInfoReviews = reviewService.findHouseInfoReview(houseInfo, page, size);
        return new ResponseEntity<>(new MultiResponseDto<>(houseInfoReviews), HttpStatus.OK);
    }
}
