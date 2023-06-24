package jeju.oneroom.review.repository;

import jeju.oneroom.review.entity.Review;
import jeju.oneroom.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ReviewRepository extends JpaRepository<Review, Long> , ReviewCustomRepository{
    // 유저가 작성 한 리뷰
    Page<Review> findByUser(User user, Pageable pageable);

    // 좋아요 순 리뷰 2개
    List<Review> findTop2ByOrderByLikesDesc();
}
