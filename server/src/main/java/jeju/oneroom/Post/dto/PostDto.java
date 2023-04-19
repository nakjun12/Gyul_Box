package jeju.oneroom.Post.dto;

import jeju.oneroom.postComment.dto.PostCommentDto;
import jeju.oneroom.user.dto.UserDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class PostDto {

    @Getter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class Post {
        private String title; // 제목
        private String content; // 내용
        private String houseAddress; // 주소
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class Patch {
        private String title; // 제목
        private String content; // 내용
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class Response {
        private Long id; // postId
        private UserDto.SimpleResponseDto writer;
        private String title; // 제목
        private String content; // 내용
        private String houseAddress;
        private int views; // 조회수
        private int likes; // 좋아요 수
        private LocalDateTime createdAt; // 작성 날짜
        private LocalDateTime modifiedAt; // 수정날짜 -> 필요한가
        private int commentsCount; // 달린 댓글 수
        private List<PostCommentDto.Response> postComments; // 게시글에 달린 댓글 리스트로 보내기
    }

    //심플 게시글 리스트
    @Getter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class SimpleResponseDto {
        private Long id; // postId;
        private String nickname;  // 글쓴이 닉네임
        private String title; // 제목
        private String content; // 내용
        private LocalDateTime createdAt; // 작성 날짜
        private LocalDateTime modifiedAt; // 수정날짜
        private int likes; // 좋아요 수
    }
}
