package jeju.oneroom.common.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Rate {
    private double interiorRate;
    private double buildingRate;
    private double trafficRate;
    private double securityRate;
    private double locationRate;
    private double avgRate;

    public Rate(double interiorRate, double buildingRate, double trafficRate, double securityRate, double locationRate, double avgRate) {
        this.interiorRate = interiorRate;
        this.buildingRate = buildingRate;
        this.trafficRate = trafficRate;
        this.securityRate = securityRate;
        this.locationRate = locationRate;
        this.avgRate = avgRate;
    }
}
