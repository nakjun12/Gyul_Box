package jeju.oneroom.review.repository;

import jeju.oneroom.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findByHouseInfo_Town_TownCode(long townCode, Pageable pageable);
    Page<Review> findByUser_Id(long userId, Pageable pageable);
}
