package jeju.oneroom.review.repository;

import jeju.oneroom.area.entity.Area;
import jeju.oneroom.houseInfo.entity.HouseInfo;
import jeju.oneroom.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ReviewCustomRepository {
    // 아이디를 통한 조회
    Optional<Review> findById(long reviewId);

    // 지역에 존재하는 리뷰 상위 5개
    List<Review> findTop5ByHouseInfo_Area(Area area);

    // 지역에 존재하는 리뷰
    Page<Review> findByHouseInfo_Area(Area area, Pageable pageable);

    // 해당 건물에 달려있는 리뷰
    Page<Review> findByHouseInfo(HouseInfo houseInfo, Pageable pageable);
}
