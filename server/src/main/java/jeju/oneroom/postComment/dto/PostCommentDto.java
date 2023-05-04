package jeju.oneroom.postComment.dto;

import jeju.oneroom.user.dto.UserDto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
public class PostCommentDto {
    @Getter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class Post {
        private String content; // 내용
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class Patch {
        private String content; // 내용
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class Response {
        private Long id; // postCommentId
        private String content; // 내용
        private LocalDateTime createdAt; // 작성 날짜
        private LocalDateTime modifiedAt; // 수정날짜
        private UserDto.SimpleResponse writer; // 작성자 정보
    }
}
