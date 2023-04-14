package jeju.oneroom.user.entity;

import jeju.oneroom.message.entity.Message;
import jeju.oneroom.review.entity.Review;
import jeju.oneroom.town.entity.Town;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="USERS")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id", insertable = false, updatable = false)
    private Long id;

    private String email;
    private String nickname;
    private String profileImageUrl;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @OneToMany(mappedBy = "sender")
    private List<Message> sends = new ArrayList<>();

    @OneToMany(mappedBy = "receiver")
    private List<Message> receives = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "town_id")
    private Town town;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();

    @Builder
    public User(Long id, String email, String nickname, LocalDateTime createdAt, LocalDateTime modifiedAt, List<Message> sends, List<Message> receives, Town town, List<Review> reviews) {
        this.email = email;
        this.nickname = nickname;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.sends = sends;
        this.receives = receives;
        this.town = town;
        this.reviews = reviews;
    }
}
