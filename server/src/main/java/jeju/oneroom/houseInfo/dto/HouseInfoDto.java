package jeju.oneroom.houseInfo.dto;

import jeju.oneroom.common.entity.Coordinate;
import jeju.oneroom.common.entity.Rate;
import jeju.oneroom.review.dto.ReviewDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class HouseInfoDto {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{
        private Long id;
        // 건물 이름 없을 경우?
        private String houseName;
        private String buildUes;
        private String buildingStructure;
        private int houseHold;
        private String useAprDay;
        // business logic으로 grndFloor와 ugrndFloor 문자열로 반환
        private String floor;
        private int elevator;
        private String platPlc;

        private Coordinate coordinate;
        private Rate rate;

        private List<ReviewDto.Response> reviews;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SimpleResponse{
        private Long id;
        // 건물 이름 없을 경우?
        // 주소 명으로 대체
        private String houseName;
        private String platPlc;
        private double avgRate;
        private int reviewCount;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SimpleCountResponse{
        private Long id;
        private int reviewCount;

        private Coordinate coordinate;
    }
}
