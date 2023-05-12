package jeju.oneroom.user.entity;

import jeju.oneroom.area.entity.Area;
import jeju.oneroom.common.entity.BaseEntity;
import jeju.oneroom.review.entity.Review;
import lombok.*;

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

    private String profileImageUrl;

    @OneToOne
    @JoinColumn(name = "area_id")
    private Area area;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();

    @Builder
    public User(Long id, String email, String nickname, Area area, List<Review> reviews) {
        this.email = email;
        this.nickname = nickname;
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
}
