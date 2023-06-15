package jeju.oneroom.message.entity;

import jeju.oneroom.common.entity.BaseEntity;
import jeju.oneroom.messageroom.entity.MessageRoom;
import jeju.oneroom.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Message extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @Setter
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Setter
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private MessageRoom messageRoom;

    @Setter
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private User sender;

    @Setter
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private User receiver;

    @Builder
    public Message(MessageRoom messageRoom, User sender, User receiver, String content) {
        this.messageRoom = messageRoom;
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }

    public void setProperties(MessageRoom messageRoom, User sender, User receiver) {
        this.messageRoom = messageRoom;
        this.sender = sender;
        this.receiver = receiver;
    }
}
