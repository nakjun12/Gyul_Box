package jeju.oneroom.houseInfo.repository;

import jeju.oneroom.area.entity.Area;
import jeju.oneroom.houseInfo.entity.HouseInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HouseInfoRepository extends JpaRepository<HouseInfo, Long> {

    // 해당 지역의 모든 건물 정보 리스트로 반환
    List<HouseInfo> findByArea(Area area);

    // 지역의 건물 정보 중에서 리뷰가 가장 많은 건물 Top20개 리스트 반환
    List<HouseInfo> findTop20ByAreaOrderByReviewCount(Area area);

    // 주소를 통한 건물 정보 리스트로 반환
    List<HouseInfo> findByPlatPlcContains(String content);
}
