package jeju.oneroom.postcomment.dto;

import jeju.oneroom.post.dto.PostDto;
import jeju.oneroom.user.dto.UserDto;
import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

public class PostCommentDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post {

        // 댓글 내용
        @NotBlank
        @Size(min = 3, message = "최소 3자 이상 입력해주세요")
        private String content;

        // 로그인 한 유저 정보
        @NotBlank
        @Pattern(regexp = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$",
                message = "올바른 이메일 형식을 입력해 주세요.")
        private String userEmail;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Patch {

        private long postCommentId;

        // 댓글 내용
        @NotBlank
        @Size(min = 3, message = "최소 3자 이상 입력해주세요")
        private String content;

        public void setPostCommentId(long postCommentId) {
            this.postCommentId = postCommentId;
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long id; // postCommentId
//        private PostDto.SimpleResponseDto post; // comment의 post 간단 정보
        private Long postId;
        private String postTitle;
        private String content; // 내용
        private LocalDateTime createdAt; // 작성 날짜
        private LocalDateTime modifiedAt; // 수정 날짜
        private UserDto.SimpleResponse writer; // 작성자 정보
    }
}
