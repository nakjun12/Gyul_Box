package jeju.oneroom.town.entity;

import jeju.oneroom.coordinate.entity.Coordinate;
import jeju.oneroom.houseInfo.entity.HouseInfo;
import jeju.oneroom.si.entity.Si;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Town {
    @Id
    @Column(name = "town_id")
    private Long townCode;

    private String townName;

    @CreatedBy
    @Column(updatable = false)
    private String createdAt;
    @LastModifiedBy
    private String ModifiedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "si_id")
    private Si si;

    @OneToMany(mappedBy = "town")
    private List<HouseInfo> houseInfos = new ArrayList<>();

    @Embedded
    private Coordinate coordinate;
}
