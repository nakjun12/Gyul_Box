package jeju.oneroom.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Patch {
        private Long id;

        @NotBlank(message = "닉네임은 공백이 될 수 없습니다")
        private String nickname;

        @NotNull(message = "지역코드는 공백이 될 수 없습니다")
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
