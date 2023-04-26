package jeju.oneroom.review.repository;

import jeju.oneroom.houseInfo.entity.HouseInfo;
import jeju.oneroom.review.entity.Review;
import jeju.oneroom.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByHouseInfo(HouseInfo houseInfo);

    Page<Review> findByUser(User user, Pageable pageable);

    List<Review> findTop5ByOrderByCreatedAtDesc(); // 최신 리뷰 5개

    List<Review> findTop5ByOrderByLikesDesc(); // 최신 리뷰 5개
}
