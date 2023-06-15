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
import org.springframework.lang.Nullable;

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

    private String mainPurpsCdNm;
    @Nullable
    private String houseName;
    private String buildUes;
    private String buildingStructure;
    private int houseHold;
    private String useAprDay;
    private int grndFloor;
    private int ugrndFloor;
    private int elevator;
    private String platPlc;

    // Formula 어노테이션을 사용하여 count 조회 최적화
    @Basic(fetch = FetchType.EAGER)
    @Formula("(select count(*) from review r where r.house_info_id = house_info_id)")
    private int reviewCount;

    // 건물 정보의 모든 리뷰에 대한 평균
    @Embedded
    private Rate rate;

    // 건물이 존재하는 지역
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id")
    private Area area;

    // 건물 정보에 기입 된 리뷰
    @OneToMany(mappedBy = "houseInfo")
    private List<Review> reviews = new ArrayList<>();

    // 건물의 위도, 경도
    @Embedded
    private Coordinate coordinate;

    @Builder
    public HouseInfo(String mainPurpsCdNm, String houseName, String buildUes, String buildingStructure, int houseHold, String useAprDay, int grndFloor, int ugrndFloor,
                     int elevator, String platPlc, Rate rate, Area area, List<Review> reviews, Coordinate coordinate) {
        this.mainPurpsCdNm = mainPurpsCdNm;
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

    // 리뷰 생성 시 건물 정보의 별점 갱신을 위한 update
    public void updateRate(Rate rate) {
        this.rate = rate;
    }

    // 건물 정보의 지역 설정
    public void setArea(Area area) {
        this.area = area;
    }

    // 건물 정보의 위도, 경도 설정
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
