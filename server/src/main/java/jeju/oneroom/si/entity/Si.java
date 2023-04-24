package jeju.oneroom.si.entity;

import jeju.oneroom.common.entity.BaseEntity;
import jeju.oneroom.common.entity.Coordinate;
import jeju.oneroom.town.entity.Town;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Si extends BaseEntity {
    @Id
    @Column(name = "si_id")
    private Long siCode;

    private String siName;

    @OneToMany(mappedBy = "si")
    private List<Town> towns;

    @Embedded
    private Coordinate coordinate;

    @Builder
    public Si(Long siCode, String siName, List<Town> towns, Coordinate coordinate) {
        this.siCode = siCode;
        this.siName = siName;
        this.towns = towns;
        this.coordinate = coordinate;
    }
}
