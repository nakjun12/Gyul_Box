package jeju.oneroom.postComment.entity;

import jeju.oneroom.Post.entitiy.Post;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostComment {
    @Id
    @GeneratedValue
    @Column(name = "postComment_id")
    private Long id; // id;

    private String content; // 내용

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post; // 양도게시글과 매핑

    @CreatedDate
    private LocalDateTime createdAt; // 작성 날짜
    @LastModifiedDate
    private LocalDateTime modifiedAt; // 수정 날짜
}
