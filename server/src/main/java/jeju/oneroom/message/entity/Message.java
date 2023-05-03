package jeju.oneroom.message.entity;

import jeju.oneroom.common.entity.BaseEntity;
import jeju.oneroom.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Message extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;

    private String content;
    private Boolean readed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User receiver;

    @Builder
    public Message(String content, Boolean readed, User sender, User receiver) {
        this.content = content;
        this.readed = readed;
        this.sender = sender;
        this.receiver = receiver;
    }
}
