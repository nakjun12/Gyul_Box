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
    private double interiorRate;
    @ColumnDefault("0")
    private double buildingRate;
    @ColumnDefault("0")
    private double trafficRate;
    @ColumnDefault("0")
    private double securityRate;
    @ColumnDefault("0")
    private double locationRate;
    @ColumnDefault("0")
    private double avgRate;

    @Builder
    public Rate(double interiorRate, double buildingRate, double trafficRate, double securityRate, double locationRate) {
        this.interiorRate = interiorRate;
        this.buildingRate = buildingRate;
        this.trafficRate = trafficRate;
        this.securityRate = securityRate;
        this.locationRate = locationRate;
        this.avgRate = Math.round(((interiorRate + buildingRate + trafficRate + securityRate + locationRate) / 5) * 100) / 100.0;
    }
}
