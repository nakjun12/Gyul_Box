package jeju.oneroom.houseInfo.dto;

import jeju.oneroom.common.entity.Coordinate;
import jeju.oneroom.common.entity.Rate;
import jeju.oneroom.review.dto.ReviewDto;

import java.util.ArrayList;
import java.util.List;

public class HouseInfoDto {
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

        private List<ReviewDto.Response> reviews = new ArrayList<>();
    }
}
