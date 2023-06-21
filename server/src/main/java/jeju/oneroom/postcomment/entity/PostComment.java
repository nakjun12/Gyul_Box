package jeju.oneroom.postcomment.entity;

import jeju.oneroom.common.entity.BaseEntity;
import jeju.oneroom.post.entity.Post;
import jeju.oneroom.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostComment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postComment_id")
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public PostComment(String content, Post post, User user) {
        this.content = content;
        this.post = post;
        this.user = user;
    }

    public void setProperties(Post post, User user) {
        this.post = post;
        this.user = user;
    }

    public void update(String content) {
        this.content = content;
    }
}
