package jeju.oneroom.area.repository;

import jeju.oneroom.area.entity.Area;
import jeju.oneroom.si.entity.Si;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AreaRepository extends JpaRepository<Area, Long> {
    List<Area> findBySi(Si si);
}
