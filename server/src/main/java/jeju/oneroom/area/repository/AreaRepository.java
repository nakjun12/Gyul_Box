package jeju.oneroom.area.repository;

import jeju.oneroom.area.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AreaRepository extends JpaRepository<Area, Long> {
    List<Area> findByAreaName(String areaName);
}
