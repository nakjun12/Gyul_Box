package jeju.oneroom.review.entity;

import jeju.oneroom.common.entity.BaseEntity;
import jeju.oneroom.common.entity.Rate;
import jeju.oneroom.houseInfo.entity.HouseInfo;
import jeju.oneroom.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity {
    @Id
    @Column(name = "review_id")
    @GeneratedValue
    private Long id;

    private String buildingName;
    private String address;
    private String advantage;
    private String disadvantage;
    private String adminCost;
    private String residenceYear;
    private String floor;
    private int likes;

    @Embedded
    private Rate rate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "houseInfo_id")
    private HouseInfo houseInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Review(String buildingName, String address, String advantage, String disadvantage, String adminCost,
                  String residenceYear, String floor, int likes, Rate rate, HouseInfo houseInfo, User user) {
        this.buildingName = buildingName;
        this.address = address;
        this.advantage = advantage;
        this.disadvantage = disadvantage;
        this.adminCost = adminCost;
        this.residenceYear = residenceYear;
        this.floor = floor;
        this.likes = likes;
        this.rate = rate;
        this.houseInfo = houseInfo;
        this.user = user;
    }
}