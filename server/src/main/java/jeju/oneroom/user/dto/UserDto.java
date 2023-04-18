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
        private String nickname; // 닉네임
        //관심 지역 추후에 추가
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class Response {
        private Long id; // id
        private String email; // 이메일
        //private String town; // user -> ResponseDto 변환 과정에서 어떻게 처리할 지 모르겠음.
        private UserDto.SimpleResponseDto writer;
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
