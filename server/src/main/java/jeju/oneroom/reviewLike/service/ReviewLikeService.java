package jeju.oneroom.reviewLike.service;

import jeju.oneroom.review.entity.Review;
import jeju.oneroom.review.repository.ReviewRepository;
import jeju.oneroom.reviewLike.entity.ReviewLike;
import jeju.oneroom.reviewLike.repository.ReviewLikeRepository;
import jeju.oneroom.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewLikeService {
    private final ReviewLikeRepository reviewLikeRepository;
    private final ReviewRepository reviewRepository;

    // 좋아요 및 취소
    // 좋아요를 누르지 않은 유저는 좋아요 추가, 좋아요를 누른 유저는 좋아요 삭제
    public void pushLike(Review review, User user) {
        checkSameUser(review, user);
        reviewLikeRepository.findByReviewAndUser(review, user)
                .ifPresentOrElse(
                        reviewLike -> reviewLikeRepository.deleteById(reviewLike.getId()),
                        () -> {
                            ReviewLike reviewLike = ReviewLike.builder().review(review).user(user).build();
                            reviewLikeRepository.save(reviewLike);
                        });
    }

    // 리뷰 작성자가 좋아요를 누를 경우 방지
    private void checkSameUser(Review review, User user) {
        if (review.getUser() == user) {
            throw new RuntimeException("SAME_USER");
        }
    }
}

