package jeju.oneroom.common.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.PrePersist;

// Review와 HouseInfo에 제공되는 평점 정보
@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Rate {
    private Double interiorRate;

    private Double buildingRate;

    private Double trafficRate;

    private Double securityRate;

    private Double locationRate;

    private Double avgRate;

    @PrePersist
    private void calculateAvgRate() {
        this.avgRate = Math.round(((interiorRate + buildingRate + trafficRate + securityRate + locationRate) / 5) * 100) / 100.0;
    }

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
