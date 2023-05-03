package jeju.oneroom.houseInfo.entity;

import jeju.oneroom.area.entity.Area;
import jeju.oneroom.common.entity.BaseEntity;
import jeju.oneroom.common.entity.Coordinate;
import jeju.oneroom.common.entity.Rate;
import jeju.oneroom.review.entity.Review;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HouseInfo extends BaseEntity {
    @Id
    @Column(name = "houseInfo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String houseName;
    private String buildUes;
    private String buildingStructure;
    private int houseHold;
    private String useAprDay;
    private int grndFloor;
    private int ugrndFloor;
    private int elevator;
    private String platPlc;
    @Basic(fetch=FetchType.LAZY)
    @Formula("(select count(*) from review r where r.house_info_id = house_info_id)")
    private int reviewCount;

    @Embedded
    private Rate rate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id")
    private Area area;

    @OneToMany(mappedBy = "houseInfo")
    private List<Review> reviews = new ArrayList<>();

    @Embedded
    private Coordinate coordinate;

    @Builder
    public HouseInfo(String houseName, String buildUes, String buildingStructure, int houseHold, String useAprDay, int grndFloor, int ugrndFloor,
                     int elevator, String platPlc, Rate rate, Area area, List<Review> reviews, Coordinate coordinate) {
        this.houseName = houseName;
        this.buildUes = buildUes;
        this.buildingStructure = buildingStructure;
        this.houseHold = houseHold;
        this.useAprDay = useAprDay;
        this.grndFloor = grndFloor;
        this.ugrndFloor = ugrndFloor;
        this.elevator = elevator;
        this.platPlc = platPlc;
        this.rate = rate;
        this.area = area;
        this.reviews = reviews;
        this.coordinate = coordinate;
    }
}
/*
level >= 9 -> 해당 시의 review 추천순 top 20에 해당하는 houseInfo 제공
findTop20byReviewLikes

level <= 8 -> 해당 동의 모든 houseInfo 제공
 */