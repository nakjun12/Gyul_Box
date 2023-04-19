package jeju.oneroom.houseInfo.entity;

import jeju.oneroom.common.entity.BaseEntity;
import jeju.oneroom.common.entity.Coordinate;
import jeju.oneroom.common.entity.Rate;
import jeju.oneroom.review.entity.Review;
import jeju.oneroom.town.entity.Town;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HouseInfo extends BaseEntity {
    @Id
    @Column(name = "houseInfo_id")
    @GeneratedValue
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
    @ColumnDefault("0")
    private int reviewCount;


    @Embedded
    private Rate rate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "town_id")
    private Town town;

    @OneToMany(mappedBy = "houseInfo")
    private List<Review> reviews = new ArrayList<>();

    @Embedded
    private Coordinate coordinate;

    @Builder
    public HouseInfo(String houseName, String buildUes, String buildingStructure, int houseHold, String useAprDay, int grndFloor, int ugrndFloor,
                     int elevator, String platPlc, Rate rate, Town town, List<Review> reviews, Coordinate coordinate) {
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
        this.town = town;
        this.reviews = reviews;
        this.coordinate = coordinate;
    }
}