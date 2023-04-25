package jeju.oneroom.review.service;

import jeju.oneroom.review.entity.Review;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReviewService {
    //리뷰 생성
    public Review createReview(Review review) {
        return review;
    }

    //리뷰 수정
    public Review updateReview(Review review) {
        return review;
    }

    //리뷰 삭제
    public void deleteReview(long reviewId) {

    }
}
