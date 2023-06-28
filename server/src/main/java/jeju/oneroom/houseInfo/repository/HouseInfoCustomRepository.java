package jeju.oneroom.houseInfo.repository;

import jeju.oneroom.houseInfo.entity.HouseInfo;

import java.util.List;

public interface HouseInfoCustomRepository {
    // 해당 지역의 모든 건물 정보 리스트로 반환
    List<HouseInfo> findByArea_AreaCode(long areaCode);

    // 지역의 건물 정보 중에서 리뷰가 가장 많은 건물 Top20개 리스트 반환
    List<HouseInfo> findTop20ByArea_AreaCodeOrderByReviewCount(long areaCode);
}
