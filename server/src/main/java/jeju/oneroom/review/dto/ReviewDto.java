package jeju.oneroom.review.dto;

import jeju.oneroom.common.entity.Rate;
import jeju.oneroom.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ReviewDto {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post{
        private String buildingName;
        private String advantage;
        private String disadvantage;
        private String adminCost;
        private String residenceYear;
        private String floor;
        private Rate rate;
        private int likes;

        // houseInfo와 매핑하기 위한 주소
        private String address;
    }

    // 리뷰하는 건물이 달라지는 경우는 delete 후 새로 post
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Patch{
        private String advantage;
        private String disadvantage;
        private String adminCost;
        private String residenceYear;
        private String floor;
        private Rate rate;
        private int likes;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{
        private String buildingName;
        private String advantage;
        private String disadvantage;
        private String adminCost;
        private String residenceYear;
        private String floor;
        private String avgRate;
        private int likes;

        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        // 작성자 정보는 머지 후 추가
        private UserDto.SimpleResponseDto writer;
    }

    // 특정 동의 추천 순 리뷰 30개
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SimpleResponse{
        private String advantage;
        private String disadvantage;
        // 최다 추천 리뷰의 평균 별점
        private double avgRate;
        private int likes;

        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        //매핑 된 HouseInfo의 주소
        private String address;
    }
}
