package jeju.oneroom.user.dto;

import lombok.*;

public class UserDto {

    @Getter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class Post {
        private String email; // 이메일
        private String nickname; // 닉네임
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class Patch {
        private Long id;
        private String nickname; // 닉네임
        private String area; // 관심지역
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class Response {
        private Long id; // id
        private String email; // 이메일
        private String area;
        private String nickname; //닉네임
        private String profileImageUrl;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class SimpleResponseDto {
        private Long id; // userId 보내기
        private String nickname; //닉네임
        private String profileImageUrl; // 프로필 이미지 URL
    }
}
