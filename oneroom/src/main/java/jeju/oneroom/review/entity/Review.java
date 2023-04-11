package jeju.oneroom.review.entity;

import jeju.oneroom.houseInfo.entity.HouseInfo;
import jeju.oneroom.town.entity.Town;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {
    @Id
    @Column(name = "review_id")
    @GeneratedValue
    private Long id;

    private String title;
    private String advantage;
    private String disadvantage;
    private String adminCost;
    private int interiorRate;
    private int buildingRate;
    private int trafficRate;
    private int securityRate;
    private int locationRate;
    private String residenceYear;
    private String floor;
    private int avgRate;

    @CreatedBy
    @Column(updatable = false)
    private String createdAt;
    @LastModifiedBy
    private String ModifiedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "houseInfo_id")
    private HouseInfo houseInfo;
}