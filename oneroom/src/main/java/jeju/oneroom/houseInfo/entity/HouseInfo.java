package jeju.oneroom.houseInfo.entity;

import jeju.oneroom.coordinate.entity.Coordinate;
import jeju.oneroom.review.entity.Review;
import jeju.oneroom.si.entity.Si;
import jeju.oneroom.town.entity.Town;
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
public class HouseInfo {
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

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;
    @LastModifiedBy
    private String lastModifiedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "town_id")
    private Town town;

    @OneToMany(mappedBy = "houseInfo")
    private List<Review> reviews = new ArrayList<>();

    @Embedded
    private Coordinate coordinate;
}