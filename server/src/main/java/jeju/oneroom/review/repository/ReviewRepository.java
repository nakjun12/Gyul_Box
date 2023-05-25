package jeju.oneroom.review.repository;

import jeju.oneroom.area.entity.Area;
import jeju.oneroom.houseInfo.entity.HouseInfo;
import jeju.oneroom.review.entity.Review;
import jeju.oneroom.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findByHouseInfo_Area(Area area, Pageable pageable);

    List<Review> findTop5ByHouseInfo_Area(Area area);

    Page<Review> findByUser(User user, Pageable pageable);

    List<Review> findTop2ByOrderByLikesDesc(); // 최신 리뷰 2개

    Page<Review> findByHouseInfo(HouseInfo houseInfo, Pageable pageable);
}
