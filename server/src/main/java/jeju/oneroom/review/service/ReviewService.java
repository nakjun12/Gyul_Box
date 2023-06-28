package jeju.oneroom.review.service;

import jeju.oneroom.area.entity.Area;
import jeju.oneroom.exception.BusinessLogicException;
import jeju.oneroom.exception.ExceptionCode;
import jeju.oneroom.houseInfo.entity.HouseInfo;
import jeju.oneroom.review.dto.ReviewDto;
import jeju.oneroom.review.entity.Review;
import jeju.oneroom.review.mapper.ReviewMapper;
import jeju.oneroom.review.repository.ReviewRepository;
import jeju.oneroom.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    //리뷰 생성
    @Transactional
    public Review createReview(ReviewDto.Post postDto, HouseInfo houseInfo, User user) {
        Review review = reviewMapper.postDtoToReview(postDto);
        review.setProperties(houseInfo, user);
        return reviewRepository.save(review);
    }

    //리뷰 수정
    @Transactional
    public Review updateReview(User user, ReviewDto.Patch patchDto) {
        Review findReview = findVerifiedReview(patchDto.getReviewId());

        if (findReview.isAuthor(user)) {
            findReview.update(patchDto.getAdvantage(), patchDto.getDisadvantage(), patchDto.getAdminCost(), patchDto.getResidenceYear(), patchDto.getFloor(), patchDto.getRate());
        } else {
            throw new BusinessLogicException(ExceptionCode.NO_PERMISSION_TO_EDIT);
        }

        return findReview;
    }

    //리뷰 삭제
    @Transactional
    public void deleteReview(User user, long reviewId) {
        Review verifiedReview = findVerifiedReview(reviewId);

        if (verifiedReview.isAuthor(user)) {
            reviewRepository.delete(findVerifiedReview(reviewId));
        } else {
            throw new BusinessLogicException(ExceptionCode.NO_PERMISSION_TO_DELETE);
        }
    }

    //단건 조회
    public ReviewDto.Response findReview(long reviewId) {
        return reviewMapper.reviewToResponseDto(findVerifiedReview(reviewId));
    }

    //추천순 리뷰 2개
    public List<ReviewDto.SimpleResponse> findTop2HottestReviews() {
        List<Review> reviews = reviewRepository.findTop2ByOrderByLikesDesc();
        return reviews.stream().map(reviewMapper::reviewToSimpleResponseDto).collect(Collectors.toList());
    }

    //유저 관심 지역에 해당하는 리뷰 중 추천수 top5
    public List<ReviewDto.SimpleResponse> findTop5UserAreaReviews(User user) {
        Area area = user.getArea();
        return reviewRepository.findTop5ByHouseInfo_Area(area).stream().map(reviewMapper::reviewToSimpleResponseDto).collect(Collectors.toList());
    }

    //유저가 작성한 리뷰 가져오기
    public Page<ReviewDto.SimpleResponse> findUserReviews(User user, int page, int size) {
        return reviewRepository.findByUser(user, PageRequest.of(page - 1, size)).map(reviewMapper::reviewToSimpleResponseDto);
    }

    //지역에 따른 리뷰 페이지네이션 적용
    public Page<ReviewDto.SimpleResponse> findAreaReviews(Area area, int page, int size) {
        return reviewRepository.findByHouseInfo_Area(area, PageRequest.of(page - 1, size)).map(reviewMapper::reviewToSimpleResponseDto);
    }

    // 리뷰가 존재하는지 확인
    public Review findVerifiedReview(long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_REVIEW));
    }

    // 건물 정보에 존재하는 리뷰
    public Page<ReviewDto.Response> findHouseInfoReview(HouseInfo houseInfo, int page, int size) {
        return reviewRepository.findByHouseInfo(houseInfo, PageRequest.of(page - 1, size)).map(reviewMapper::reviewToResponseDto);
    }
}
