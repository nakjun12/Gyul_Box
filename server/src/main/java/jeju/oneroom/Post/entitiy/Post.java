package jeju.oneroom.Post.entitiy;

import jeju.oneroom.common.entity.BaseEntity;
import jeju.oneroom.postComment.entity.PostComment;
import jeju.oneroom.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String title;
    private String content;
    private int views;
    private int likes;
    private String houseAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post")
    private List<PostComment> postComments;

    @Builder
    public Post(String title, String content, int views, int likes, String houseAddress, User user, List<PostComment> postComments) {
        this.title = title;
        this.content = content;
        this.views = views;
        this.likes = likes;
        this.houseAddress = houseAddress;
        this.user = user;
        this.postComments = postComments;
    }
}
