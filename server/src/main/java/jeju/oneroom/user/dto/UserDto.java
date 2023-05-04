package jeju.oneroom.user.dto;

import lombok.*;

public class UserDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post {
        private String email;
        private String nickname;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Patch {
        private Long id;
        private String nickname;
        private Long areaCode; // 관심지역

        public void setUserId(Long id) {
            this.id = id;
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private String email;
        private String areaName;
        private String nickname;
        private String profileImageUrl; // 프로필 이미지 URL
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SimpleResponse {
        private Long id;
        private String nickname;
        private String profileImageUrl;
    }
}
