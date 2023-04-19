package jeju.oneroom.town.repository;

import jeju.oneroom.si.entity.Si;
import jeju.oneroom.town.entity.Town;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TownRepository extends JpaRepository<Town, Long> {
    List<Town> findBySi(Si si);
}
