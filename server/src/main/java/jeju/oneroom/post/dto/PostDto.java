package jeju.oneroom.post.dto;

import jeju.oneroom.houseInfo.dto.HouseInfoDto;
import jeju.oneroom.postcomment.dto.PostCommentDto;
import jeju.oneroom.user.dto.UserDto;
import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

public class PostDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post {
        // 제목
        @NotBlank
        @Size(min = 10, message = "최소 10자 이상 입력해주세요")
        private String title;

        // 내용
        @NotBlank
        @Size(min = 50, message = "최소 50자 이상 입력해주세요")
        private String content;

        // houseInfo와 매핑하기 위한 id
        @NotNull
        private Long houseInfoId;

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
        private Long postId;

        // 제목
        @NotBlank
        @Size(min = 10, message = "최소 10자 이상 입력해주세요")
        private String title;

        // 내용
        @NotBlank
        @Size(min = 50, message = "최소 50자 이상 입력해주세요")
        private String content;

        // 로그인 한 유저 정보
        @NotBlank
        @Pattern(regexp = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$",
                message = "올바른 이메일 형식을 입력해 주세요.")
        private String userEmail;

        public void setPostId(Long postId) {
            this.postId = postId;
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Delete {
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
    public static class Response {
        private Long id; // postId
        private UserDto.SimpleResponse writer;  // 작성자
        private String title; // 제목
        private String content; // 내용
        private HouseInfoDto.SimpleContentResponse houseInfo; // 집 정보
        private int likes; // 좋아요 수
        private int views; // 조회 수
        private LocalDateTime createdAt; // 작성 날짜
        private LocalDateTime modifiedAt; // 수정 날짜
        private int commentsCount; // 달린 댓글 수
        private List<PostCommentDto.Response> postComments; // 게시글에 달린 댓글 리스트
    }

    //심플 게시글 리스트
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SimpleResponseDto {
        private Long id; // postId;
        private String nickname;  // 글쓴이 닉네임
        private String title; // 제목
        private String content; // 내용
        private LocalDateTime createdAt; // 작성 날짜
        private LocalDateTime modifiedAt; // 수정 날짜
        private int likes; // 좋아요 수
        private int views; // 조회 수
    }
}
