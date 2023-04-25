package jeju.oneroom.area.entity;

import jeju.oneroom.common.entity.BaseEntity;
import jeju.oneroom.common.entity.Coordinate;
import jeju.oneroom.houseInfo.entity.HouseInfo;
import jeju.oneroom.si.entity.Si;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Area extends BaseEntity {
    @Id
    @Column(name = "town_id")
    private Long townCode;

    private String townName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "si_id")
    private Si si;

    @OneToMany(mappedBy = "area")
    private List<HouseInfo> houseInfos = new ArrayList<>();

    @Embedded
    private Coordinate coordinate;

    @Builder
    public Area(Long townCode, String townName, Si si, List<HouseInfo> houseInfos, Coordinate coordinate) {
        this.townCode = townCode;
        this.townName = townName;
        this.si = si;
        this.houseInfos = houseInfos;
        this.coordinate = coordinate;
    }
}
