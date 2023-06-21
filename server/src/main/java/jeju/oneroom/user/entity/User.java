package jeju.oneroom.user.entity;

import jeju.oneroom.area.entity.Area;
import jeju.oneroom.common.entity.BaseEntity;
import jeju.oneroom.post.entity.Post;
import jeju.oneroom.review.entity.Review;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USERS")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Column
    private String profileImageUrl;

    @OneToOne
    @JoinColumn(name = "area_id")
    private Area area;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    // 유저 삭제 시, 연관된 모든 게시글 삭제
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(nullable = false)
    private List<String> roles = new ArrayList<>();

    @Builder
    public User(Long id, String email, String nickname, String profileImageUrl, Area area, List<Review> reviews) {
        this.email = email;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.area = area;
        this.reviews = reviews;
        this.id = id;
    }

    public void update(String nickname, Area area) {
        this.nickname = nickname;
        this.area = area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
