package jeju.oneroom.common.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Rate {
    @ColumnDefault("0")
    private Double interiorRate;
    @ColumnDefault("0")
    private Double buildingRate;
    @ColumnDefault("0")
    private Double trafficRate;
    @ColumnDefault("0")
    private Double securityRate;
    @ColumnDefault("0")
    private Double locationRate;
    // 프론트에서 안뜨게 처리하자.
    @ColumnDefault("0")
    private Double avgRate;

    @Builder
    public Rate(Double interiorRate, Double buildingRate, Double trafficRate, Double securityRate, Double locationRate) {
        this.interiorRate = interiorRate != null ? interiorRate : 0;
        this.buildingRate = buildingRate != null ? buildingRate : 0;
        this.trafficRate = trafficRate != null ? trafficRate : 0;
        this.securityRate = securityRate != null ? securityRate : 0;
        this.locationRate = locationRate != null ? locationRate : 0;
        this.avgRate = Math.round(((this.interiorRate + this.buildingRate + this.trafficRate + this.securityRate + this.locationRate) / 5) * 100) / 100.0;
    }
}
