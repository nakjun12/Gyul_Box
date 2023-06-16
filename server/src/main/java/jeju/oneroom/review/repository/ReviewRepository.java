package jeju.oneroom.review.repository;

import jeju.oneroom.area.entity.Area;
import jeju.oneroom.houseInfo.entity.HouseInfo;
import jeju.oneroom.review.entity.Review;
import jeju.oneroom.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 아이디를 통한 조회
    @EntityGraph(attributePaths = "user")
    Optional<Review> findById(long reviewId);

    // 지역에 존재하는 리뷰
    Page<Review> findByHouseInfo_Area(Area area, Pageable pageable);

    // 지역에 존재하는 리뷰 상위 5개
    List<Review> findTop5ByHouseInfo_Area(Area area);

    // 유저가 작성 한 리뷰
    Page<Review> findByUser(User user, Pageable pageable);

    // 좋아요 순 리뷰 2개
    List<Review> findTop2ByOrderByLikesDesc();

    // 해당 건물에 달려있는 리뷰
    @EntityGraph(attributePaths = "user")
    Page<Review> findByHouseInfo(HouseInfo houseInfo, Pageable pageable);
}
