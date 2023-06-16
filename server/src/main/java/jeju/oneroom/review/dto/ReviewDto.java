package jeju.oneroom.review.dto;

import jeju.oneroom.common.entity.Rate;
import jeju.oneroom.user.dto.UserDto;
import jeju.oneroom.validation.ValidRate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ReviewDto {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post {
        @NotBlank
        private String buildingName;

        @NotBlank
        private String advantage;

        @NotBlank
        private String disadvantage;

        @NotBlank
        private String adminCost;

        @NotBlank
        private String residenceYear;

        @NotBlank
        private String floor;

        @NotBlank
        private String buildingType;

        @ValidRate
        private Rate rate;

        // houseInfo와 매핑하기 위한 id
        @NotNull
        private Long houseInfoId;

        // 로그인 한 유저 정보
        @NotBlank
        private String userEmail;
    }

    // 리뷰하는 건물이 달라지는 경우는 delete 후 새로 post
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Patch {
        private Long reviewId;

        @NotBlank
        private String advantage;

        @NotBlank
        private String disadvantage;

        @NotBlank
        private String adminCost;

        @NotBlank
        private String residenceYear;

        @NotBlank
        private String floor;

        @NotBlank
        private String buildingType;

        @ValidRate
        private Rate rate;

        public void setReviewId(Long reviewId) {
            this.reviewId = reviewId;
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long reviewId;
        private String buildingName;
        private String advantage;
        private String disadvantage;
        private String adminCost;
        private String residenceYear;
        private String floor;
        private String buildingType;
        private Double avgRate;
        private long likes;

        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        // 작성자 정보는 머지 후 추가
        private UserDto.SimpleResponse writer;
    }

    // 특정 동의 추천 순 리뷰 30개
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SimpleResponse {
        private Long reviewId;
        private String advantage;
        private String disadvantage;
        // 최다 추천 리뷰의 평균 별점
        private Double avgRate;
        private long likes;

        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        // 베너 클릭 시 get요청을 위한 houseInfo id
        private Long houseInfo;
    }
}
