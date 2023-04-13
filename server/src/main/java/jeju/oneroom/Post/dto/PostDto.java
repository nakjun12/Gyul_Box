package jeju.oneroom.Post.dto;

import jeju.oneroom.postComment.dto.PostCommentDto;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostDto {
    public static class Post {
        private String title; // 제목
        private String content; // 내용
        private String houseAddress; // 주소
    }

    public static class Patch {
        private String title; // 제목
        private String content; // 내용
    }

    public static class Response {
        private Long id; // postId
        private String nickname;  // 글쓴이 넣기
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
    public static class SimpleResponseDto {
        private Long id; // postId;
        private String nickname;  // 글쓴이 넣기
        private String title; // 제목
        private String content; // 내용
        private LocalDateTime createdAt; // 작성 날짜
        private LocalDateTime modifiedAt; // 수정날짜
        private int likes; // 좋아요 수
    }
}
