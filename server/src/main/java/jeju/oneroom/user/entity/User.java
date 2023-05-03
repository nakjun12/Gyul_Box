package jeju.oneroom.user.entity;

import jeju.oneroom.area.entity.Area;
import jeju.oneroom.common.entity.BaseEntity;
import jeju.oneroom.message.entity.Message;
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
    @Column(name = "user_id", insertable = false, updatable = false)
    private Long id;

    private String email;
    private String nickname;
    private String profileImageUrl;

    @OneToMany(mappedBy = "sender")
    private List<Message> sends = new ArrayList<>();

    @OneToMany(mappedBy = "receiver")
    private List<Message> receives = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "area_id")
    private Area area;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();

    @Builder
    public User(Long id, String email, String nickname, List<Message> sends, List<Message> receives, Area area, List<Review> reviews) {
        this.email = email;
        this.nickname = nickname;
        this.sends = sends;
        this.receives = receives;
        this.area = area;
        this.reviews = reviews;
        this.id = id;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}
