package jeju.oneroom.houseInfo.dto;

import jeju.oneroom.common.entity.Coordinate;
import jeju.oneroom.common.entity.Rate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class HouseInfoDto {
    // 건물 정보 전체 Response
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long id;
        // 건물 이름 없을 경우 platPlc로 변경하여 사용
        private String houseName;
        private String buildUes;
        private String buildingStructure;
        private int houseHold;
        private String useAprDay;
        // business logic으로 grndFloor와 ugrndFloor 문자열로 반환
        private String floor;
        private int elevator;
        private String platPlc;

        // 건물의 위도 경도
        private Coordinate coordinate;
        // 건물에 저장된 리뷰의 종합 점수
        private Rate rate;
    }

    // 지도에서 노드 클릭 시 제공되는 건물 간략 정보
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SimpleResponse {
        private Long id;
        // 건물 이름 없을 경우? 주소 명으로 대체
        private String houseName;
        private String platPlc;
        private Double avgRate;
        private int reviewCount;
    }

    // 지도에 존재하는 노드 정보
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SimpleCountResponse {
        private Long id;
        private int reviewCount;

        private Coordinate coordinate;
    }

    // 주소를 통한 건물 정보 검색 시 제공되는 정보
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SimpleContentResponse {
        private Long id;
        private String houseName;
        private String platPlc;
    }
}
