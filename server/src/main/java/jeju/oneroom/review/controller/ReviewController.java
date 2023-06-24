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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final UserService userService;
    private final AreaService areaService;
    private final HouseInfoService houseInfoService;

    @PostMapping("/reviews")
    public ResponseEntity<?> post(@Valid @RequestBody ReviewDto.Post postDto) {
        // 유효한 건물 정보, 유저 정보 확인
        HouseInfo houseInfo = houseInfoService.findVerifiedHouseInfo(postDto.getHouseInfoId());
        User user = userService.verifyExistsUserByEmail(postDto.getUserEmail());
        // houseInfo와 user 매핑하며 review 생성
        Review review = reviewService.createReview(postDto, houseInfo, user);
        // 건물 정보에 기입된 평점이 있는지 확인 후 별점 설정
        houseInfoService.checkRate(houseInfo, postDto);
        return new ResponseEntity<>(review.getId(), HttpStatus.CREATED);
    }

    @PatchMapping("/reviews/{review-id}")
    public ResponseEntity<?> patch(@Valid @RequestBody ReviewDto.Patch patchDto,
                                   @PathVariable("review-id") @Positive long reviewId) {
        // patchDto에 Id를 넣어 서비스 단에 제공
        patchDto.setReviewId(reviewId);
        User user = userService.verifyExistsUserByEmail(patchDto.getUserEmail());

        Review review = reviewService.updateReview(user, patchDto);

        return new ResponseEntity<>(review.getId(), HttpStatus.OK);
    }

    @DeleteMapping("/reviews/{review-id}")
    public ResponseEntity<?> delete(@PathVariable("review-id") @Positive long reviewId,
                                    @Valid @RequestBody ReviewDto.Delete deleteDto) {
        User user = userService.verifyExistsUserByEmail(deleteDto.getUserEmail());

        reviewService.deleteReview(user, reviewId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //  리뷰 단일 조회
    @GetMapping("/reviews/{review-id}")
    public ResponseEntity<?> getReview(@PathVariable("review-id") @Positive long reviewId) {
        ReviewDto.Response findReview = reviewService.findReview(reviewId);
        return new ResponseEntity<>(findReview, HttpStatus.OK);
    }

    // 추천순 리뷰 2개
    @GetMapping("/reviews/hottest2")
    public ResponseEntity<?> getTop2HottestReviews() {
        List<ReviewDto.SimpleResponse> top2HottestReviews = reviewService.findTop2HottestReviews();
        return new ResponseEntity<>(new ListResponseDto<>(top2HottestReviews), HttpStatus.OK);
    }

    // 유저 관심 지역 추천 순 리뷰 5개
    @GetMapping("users/{user-id}/user-areas/reviews")
    public ResponseEntity<?> getTop5UserAreaReviews(@PathVariable("user-id") @Positive long userId) {
        User user = userService.verifyExistsUser(userId);
        List<ReviewDto.SimpleResponse> userAreaReviews = reviewService.findTop5UserAreaReviews(user);
        return new ResponseEntity<>(new ListResponseDto<>(userAreaReviews), HttpStatus.OK);
    }

    // 유저 리뷰 찾기
    @GetMapping("users/{user-id}/reviews")
    public ResponseEntity<?> getUserReviews(@RequestParam int page,
                                            @RequestParam int size,
                                            @PathVariable("user-id") @Positive long userId) {
        User user = userService.verifyExistsUser(userId);
        Page<ReviewDto.SimpleResponse> userReviews = reviewService.findUserReviews(user, page, size);
        return new ResponseEntity<>(new MultiResponseDto<>(userReviews), HttpStatus.OK);
    }

    // Area에 따른 거주 리뷰 페이징
    @GetMapping("areas/{area-id}/reviews")
    public ResponseEntity<?> getAreaReviews(@RequestParam int page,
                                            @RequestParam int size,
                                            @PathVariable("area-id") @Positive long areaCode) {
        Area area = areaService.findVerifiedAreaByAreaCode(areaCode);
        Page<ReviewDto.SimpleResponse> areaReviews = reviewService.findAreaReviews(area, page, size);
        return new ResponseEntity<>(new MultiResponseDto<>(areaReviews), HttpStatus.OK);
    }

    // houseInfo에 따른 Review Slice로 for 무한스크롤
    @GetMapping("houseInfos/{houseInfo-id}/reviews")
    public ResponseEntity<?> getHouseInfoReviews(@RequestParam int page,
                                                 @RequestParam int size,
                                                 @PathVariable("houseInfo-id") @Positive long houseInfoId) {
        HouseInfo houseInfo = houseInfoService.findVerifiedHouseInfo(houseInfoId);
        Page<ReviewDto.Response> houseInfoReviews = reviewService.findHouseInfoReview(houseInfo, page, size);
        return new ResponseEntity<>(new MultiResponseDto<>(houseInfoReviews), HttpStatus.OK);
    }
}