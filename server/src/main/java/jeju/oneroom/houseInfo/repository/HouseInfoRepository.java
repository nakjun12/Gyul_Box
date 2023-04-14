package jeju.oneroom.houseInfo.repository;

import jeju.oneroom.houseInfo.entity.HouseInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseInfoRepository extends JpaRepository<HouseInfo, Long> {
}
