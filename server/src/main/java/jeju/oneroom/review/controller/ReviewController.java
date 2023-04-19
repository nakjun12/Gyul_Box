package jeju.oneroom.review.controller;

import jeju.oneroom.common.dto.ListResponseDto;
import jeju.oneroom.common.dto.MultiResponseDto;
import jeju.oneroom.houseInfo.entity.HouseInfo;
import jeju.oneroom.houseInfo.repository.HouseInfoRepository;
import jeju.oneroom.review.dto.ReviewDto;
import jeju.oneroom.review.entity.Review;
import jeju.oneroom.review.mapper.ReviewMapper;
import jeju.oneroom.review.repository.ReviewRepository;
import jeju.oneroom.town.entity.Town;
import jeju.oneroom.town.repository.TownRepository;
import jeju.oneroom.user.entity.User;
import jeju.oneroom.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final UserRepository userRepository;
    private final TownRepository townRepository;
    private final HouseInfoRepository houseInfoRepository;

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
        Town town = user.getTown();
        List<HouseInfo> houseInfos = houseInfoRepository.findByTown(town);
        List<ReviewDto.SimpleResponse> responses = new ArrayList<>();
        for (HouseInfo houseInfo:  houseInfos) {
            List<ReviewDto.SimpleResponse>  tmp = reviewRepository.findByHouseInfo(houseInfo).stream().map(reviewMapper::reviewToSimpleResponseDto).collect(Collectors.toList());
            responses.addAll(tmp);
        }
        return new ResponseEntity<>(new ListResponseDto<>(responses), HttpStatus.OK);
    }

    // 유저 리뷰 찾기  추후 findUserTownReviews 메서드와 통합 시도.
    @GetMapping("users/{user-id}/reviews")
    public ResponseEntity<?> findUserReviews(@RequestParam int page,
                                             @RequestParam int size,
                                             @PathVariable("user-id") long userId) {
        User user = userRepository.findById(userId).orElse(null);
        Page<ReviewDto.SimpleResponse> responses = reviewRepository.findByUser(user, PageRequest.of(page - 1, size)).map(reviewMapper::reviewToSimpleResponseDto);

        return new ResponseEntity<>(new MultiResponseDto<>(responses), HttpStatus.OK);
    }

    // Town에 따른 거주 리뷰 아마 30개?
    @GetMapping("towns/{town-id}/reviews")
    public ResponseEntity<?> findTownReviews(@RequestParam int page,
                                             @RequestParam int size,
                                             @PathVariable("town-id") long townCode) {
        Town town = townRepository.findById(townCode).orElse(null);
        List<HouseInfo> houseInfos = houseInfoRepository.findByTown(town);
        List<ReviewDto.SimpleResponse> responses = new ArrayList<>();
        for (HouseInfo houseInfo:  houseInfos) {
            List<ReviewDto.SimpleResponse>  tmp = reviewRepository.findByHouseInfo(houseInfo).stream().map(reviewMapper::reviewToSimpleResponseDto).collect(Collectors.toList());
            responses.addAll(tmp);
        }
        return new ResponseEntity<>(new ListResponseDto<>(responses), HttpStatus.OK);
    }
}
