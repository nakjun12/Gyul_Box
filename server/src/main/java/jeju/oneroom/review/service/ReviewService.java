package jeju.oneroom.review.service;

import jeju.oneroom.area.entity.Area;
import jeju.oneroom.area.repository.AreaRepository;
import jeju.oneroom.houseInfo.entity.HouseInfo;
import jeju.oneroom.houseInfo.repository.HouseInfoRepository;
import jeju.oneroom.review.dto.ReviewDto;
import jeju.oneroom.review.entity.Review;
import jeju.oneroom.review.mapper.ReviewMapper;
import jeju.oneroom.review.repository.ReviewRepository;
import jeju.oneroom.user.entity.User;
import jeju.oneroom.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final UserRepository userRepository;
    private final HouseInfoRepository houseInfoRepository;
    private final AreaRepository areaRepository;

    //리뷰 생성
    public Review createReview(ReviewDto.Post postDto) {
        Review review = reviewMapper.postDtoToReview(postDto);
        return reviewRepository.save(review);
    }

    //리뷰 수정
    public Review updateReview(ReviewDto.Patch patchDto, long reviewId) {
        return null;
    }

    //단건 조회
    public ReviewDto.Response findReview(long reviewId) {
        Review findReview = findVerifiedReview(reviewId);
        return reviewMapper.reviewToResponseDto(findReview);
    }

    //최신순 리뷰 5개
    public List<ReviewDto.SimpleResponse> findTop5LatestReviews() {
        List<Review> top5ByOrderByCreatedAtDesc = reviewRepository.findTop5ByOrderByCreatedAtDesc();
        return top5ByOrderByCreatedAtDesc.stream().map(m -> reviewMapper.reviewToSimpleResponseDto(m)).collect(Collectors.toList());
    }

    //추천순 리뷰 5개
    public List<ReviewDto.SimpleResponse> findTop5HottestReviews() {
        List<Review> top5ByOrderByCreatedAtDesc = reviewRepository.findTop5ByOrderByLikesDesc();
        return top5ByOrderByCreatedAtDesc.stream().map(m -> reviewMapper.reviewToSimpleResponseDto(m)).collect(Collectors.toList());
    }

    //유저 관심 지역에 해당하는 리뷰 중 추천수 top5
    public List<ReviewDto.SimpleResponse> findUserAreaReviews(long userId) {
        User user = userRepository.findById(userId).orElse(null);
        Area area = user.getArea();
        List<HouseInfo> houseInfos = houseInfoRepository.findByArea(area);
        List<ReviewDto.SimpleResponse> responses = new ArrayList<>();
        for (HouseInfo houseInfo : houseInfos) {
            List<ReviewDto.SimpleResponse> tmp = reviewRepository.findByHouseInfo(houseInfo).stream().map(reviewMapper::reviewToSimpleResponseDto).collect(Collectors.toList());
            responses.addAll(tmp);
        }
        return responses;
    }

    //유저가 작성한 리뷰 가져오기
    public Page<ReviewDto.SimpleResponse> findUserReviews(long userId, int page, int size) {
        User user = userRepository.findById(userId).orElse(null);
        Page<ReviewDto.SimpleResponse> responses = reviewRepository.findByUser(user, PageRequest.of(page - 1, size)).map(reviewMapper::reviewToSimpleResponseDto);
        return responses;
    }

    //지역에 따른 리뷰 x개 가져오기.
    public List<ReviewDto.SimpleResponse> findAreaReviews(long areaCode, int page, int size) {
        Area area = areaRepository.findById(areaCode).orElse(null);
        List<HouseInfo> houseInfos = houseInfoRepository.findByArea(area);
        List<ReviewDto.SimpleResponse> responses = new ArrayList<>();
        for (HouseInfo houseInfo : houseInfos) {
            List<ReviewDto.SimpleResponse> tmp = reviewRepository.findByHouseInfo(houseInfo).stream().map(reviewMapper::reviewToSimpleResponseDto).collect(Collectors.toList());
            responses.addAll(tmp);
        }
        return responses;
    }

    //리뷰 삭제
    public void deleteReview(long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    //좋아요 기능
    public void likeReview(long reviewId, long userId) {

    }

    // 리뷰가 존재하는지 확인
    private Review findVerifiedReview(long reviewId) {
        Optional<Review> optionalOrder = reviewRepository.findById(reviewId);
        Review findReview =
                optionalOrder.orElseThrow(() ->
                        new RuntimeException("REVIEW_NOT_FOUND"));
//                        new BusinessLogicException(ExceptionCode.REVIEW_NOT_FOUND));
        return findReview;
    }
}
