package jeju.oneroom.area.entity;

import jeju.oneroom.common.entity.BaseEntity;
import jeju.oneroom.common.entity.Coordinate;
import jeju.oneroom.houseInfo.entity.HouseInfo;
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
    @Column(name = "area_id")
    private Long areaCode;

    private String areaName;

    @OneToMany(mappedBy = "area")
    private List<HouseInfo> houseInfos = new ArrayList<>();

    @Embedded
    private Coordinate coordinate;

    @Builder
    public Area(Long areaCode, String areaName, List<HouseInfo> houseInfos, Coordinate coordinate) {
        this.areaCode = areaCode;
        this.areaName = areaName;
        this.houseInfos = houseInfos;
        this.coordinate = coordinate;
    }
}
