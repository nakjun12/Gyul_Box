package jeju.oneroom.reviewLike.repository;

import jeju.oneroom.review.entity.Review;
import jeju.oneroom.reviewLike.entity.ReviewLike;
import jeju.oneroom.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Long> {
    Optional<ReviewLike> findByReviewAndUser(Review review, User user);
}
