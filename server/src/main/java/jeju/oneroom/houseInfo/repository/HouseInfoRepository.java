package jeju.oneroom.houseInfo.repository;

import jeju.oneroom.area.entity.Area;
import jeju.oneroom.houseInfo.entity.HouseInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HouseInfoRepository extends JpaRepository<HouseInfo, Long> {
    Optional<HouseInfo> findByPlatPlc(String address);

    List<HouseInfo> findByArea(Area area);

    List<HouseInfo> findTop20ByAreaOrderByReviewCount(Area area);
}
