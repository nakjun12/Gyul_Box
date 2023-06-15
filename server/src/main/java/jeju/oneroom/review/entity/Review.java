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
    private String buildingType;
    // 리뷰의 좋아요 - Count쿼리 최적화를 위해 @Formula로 관리
    @Basic(fetch = FetchType.LAZY)
    @Formula("(select count(*) from review_like l where l.review_id = review_id)")
    private long likes;

    @Embedded
    private Rate rate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "houseInfo_id")
    private HouseInfo houseInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "review", orphanRemoval = true)
    private List<ReviewLike> reviewLikes = new ArrayList<>();


    @Builder
    public Review(String buildingName, String address, String advantage, String disadvantage, String adminCost,
                  String residenceYear, String floor, String buildingType, Rate rate, HouseInfo houseInfo, User user) {
        this.buildingName = buildingName;
        this.address = address;
        this.advantage = advantage;
        this.disadvantage = disadvantage;
        this.adminCost = adminCost;
        this.residenceYear = residenceYear;
        this.floor = floor;
        this.buildingType = buildingType;
        this.rate = rate;
        this.houseInfo = houseInfo;
        this.user = user;
    }

    // Update 가능한 요소만 추가
    public void update(String advantage, String disadvantage, String adminCost, String residenceYear, String floor, Rate rate) {
        this.advantage = advantage;
        this.disadvantage = disadvantage;
        this.adminCost = adminCost;
        this.residenceYear = residenceYear;
        this.floor = floor;
        this.rate = rate;
    }

    // create 후 연관관계 매핑을 위한 매서드
    public void setProperties(HouseInfo houseInfo, User user) {
        this.houseInfo = houseInfo;
        this.user = user;
    }
}