package jeju.oneroom.review.entity;

import jeju.oneroom.common.entity.Rate;
import jeju.oneroom.houseInfo.entity.HouseInfo;
import jeju.oneroom.user.entity.User;
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

    @CreatedBy
    @Column(updatable = false)
    private String createdAt;
    @LastModifiedBy
    private String ModifiedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "houseInfo_id")
    private HouseInfo houseInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}