package jeju.oneroom.review.entity;

import jeju.oneroom.common.entity.BaseEntity;
import jeju.oneroom.common.entity.Rate;
import jeju.oneroom.houseInfo.entity.HouseInfo;
import jeju.oneroom.reviewLike.entity.ReviewLike;
import jeju.oneroom.user.entity.User;
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
public class Review extends BaseEntity {
    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String buildingName;
    private String address;
    private String advantage;
    private String disadvantage;
    private String adminCost;
    private String residenceYear;
    private String floor;
    @Basic(fetch = FetchType.LAZY)
    @Formula("(select count(*) from review_like l where l.review_id = review_id)")
    private long likes;
//    @Formula("(select count(*) from review_like l where l.review_id = review_id)")
//    private long weekLikes;

    @Embedded
    private Rate rate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "houseInfo_id")
    private HouseInfo houseInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "review")
    private List<ReviewLike> reviewLikes = new ArrayList<>();

    @Builder
    public Review(String buildingName, String address, String advantage, String disadvantage, String adminCost,
                  String residenceYear, String floor, Rate rate, HouseInfo houseInfo, User user) {
        this.buildingName = buildingName;
        this.address = address;
        this.advantage = advantage;
        this.disadvantage = disadvantage;
        this.adminCost = adminCost;
        this.residenceYear = residenceYear;
        this.floor = floor;
        this.rate = rate;
        this.houseInfo = houseInfo;
        this.user = user;
    }

    public void update(String advantage, String disadvantage, String adminCost, String residenceYear, String floor, Rate rate) {
        this.advantage = advantage;
        this.disadvantage = disadvantage;
        this.adminCost = adminCost;
        this.residenceYear = residenceYear;
        this.floor = floor;
        this.rate = rate;
    }

    public void setProperties(HouseInfo houseInfo, User user) {
        this.houseInfo = houseInfo;
        this.user = user;
    }
}