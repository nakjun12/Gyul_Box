package jeju.oneroom.user.dto;

import jeju.oneroom.town.entity.Town;
import lombok.Getter;

@Getter
public class UserDto {
    public static class Post {
        private String email; // 이메일
        private String nickname; // 닉네임
    }

    public static class Patch {
        private String nickname; // 닉네임
    }

    public static class Response {
        private Long id; // id
        private String email; // 이메일
        private String nickname; // 닉네임
        private String profileImageUrl; // 프로필 이미지 URL
        private String town;
    }

    public static class SimpleResponseDto {
        private Long id; // userId 보내기
        private String nickname; //닉네임
        private String profileImageUrl; // 프로필 이미지 URL
    }
}
