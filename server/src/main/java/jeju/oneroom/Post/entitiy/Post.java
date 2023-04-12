package jeju.oneroom.Post.entitiy;

import jeju.oneroom.houseInfo.entity.HouseInfo;
import jeju.oneroom.user.entity.User;
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
public class Post {
    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String title;
    private String content;
    private int views;
    private int likes;
    // 게시글에 달린 댓글 수는 DTO에 추가

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "houseInfo_id")
    private HouseInfo houseInfo;
}
