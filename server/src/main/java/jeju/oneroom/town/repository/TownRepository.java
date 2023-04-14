package jeju.oneroom.town.repository;

import jeju.oneroom.town.entity.Town;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TownRepository extends JpaRepository<Town, Long> {
}
