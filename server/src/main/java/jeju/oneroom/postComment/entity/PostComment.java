package jeju.oneroom.postComment.entity;

import jeju.oneroom.Post.entitiy.Post;
import jeju.oneroom.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostComment extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "postComment_id")
    private Long id; // id;

    private String content; // 내용

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post; // 양도게시글과 매핑

    @Builder
    public PostComment(String content, Post post) {
        this.content = content;
        this.post = post;
    }
}
