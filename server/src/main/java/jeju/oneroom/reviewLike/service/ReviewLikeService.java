package jeju.oneroom.reviewLike.service;

import jeju.oneroom.review.entity.Review;
import jeju.oneroom.review.repository.ReviewRepository;
import jeju.oneroom.reviewLike.entity.ReviewLike;
import jeju.oneroom.reviewLike.repository.ReviewLikeRepository;
import jeju.oneroom.user.entity.User;
import jeju.oneroom.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewLikeService {
    private final ReviewLikeRepository reviewLikeRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    // 좋아요 및 취소
    // user 의존성 수정
    @Transactional
    public void pushLike(long reviewId, long userId) {
        Review review = findVerifiedReview(reviewId);
        User user = findVerifiedUser(userId);
        checkSameUser(review, user);
        reviewLikeRepository.findByReviewAndUser(review, user)
                .ifPresentOrElse(
                        reviewLike -> reviewLikeRepository.deleteById(reviewLike.getId()),
                        () -> {
                            ReviewLike reviewLike = ReviewLike.builder().review(review).user(user).build();
                            reviewLikeRepository.save(reviewLike);
                        });
    }

    private void checkSameUser(Review review, User user) {
        if (review.getUser() == user) {
            throw new RuntimeException("SAME_USER");
        }
    }

// userRepository 의존성 문제 해결 ...........................................................................................
    private User findVerifiedUser(long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("USER_NOT_FOUND"));
    }

    private Review findVerifiedReview(long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(() -> new RuntimeException("REVIEW_NOT_FOUND"));
    }
}

