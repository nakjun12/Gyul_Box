package jeju.oneroom.si.entity;

import jeju.oneroom.common.entity.Coordinate;
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
public class Si {
    @Id
    @Column(name = "si_id")
    private Long siCode;

    private String siName;

    @CreatedBy
    @Column(updatable = false)
    private String createdAt;
    @LastModifiedBy
    private String ModifiedAT;

    @OneToMany(mappedBy = "si")
    private List<Town> towns = new ArrayList<>();

    @Embedded
    private Coordinate coordinate;
}
