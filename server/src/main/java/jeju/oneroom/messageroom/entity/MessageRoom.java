package jeju.oneroom.messageroom.entity;

import jeju.oneroom.common.entity.BaseEntity;
import jeju.oneroom.message.entity.Message;
import jeju.oneroom.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageRoom extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageRoomId;

    @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MessageRoomStatus messageRoomStatus;

    @Column(nullable = true)
    private String lastMessage;

    @Column(nullable = true)
    private Long lastSenderId;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = true)
    private User sender;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", nullable = true)
    private User receiver;

    @OrderBy("messageId")
    @OneToMany(mappedBy = "messageRoom", cascade = CascadeType.ALL)
    private Set<Message> messages = new LinkedHashSet<>();

    @Builder
    public MessageRoom(Long messageRoomId, User sender, User receiver) {
        this.messageRoomId = messageRoomId;
        this.sender = sender;
        this.receiver = receiver;
    }

    public void setProperties(User sender, User receiver) {
        this.sender = sender;
        this.receiver = receiver;
        this.messageRoomStatus = MessageRoomStatus.UNCHECK;
    }

    public void setMessageRoom(Message message) {
        this.lastMessage = message.getContent();
        this.lastSenderId = message.getSender().getId();
        this.messageRoomStatus = MessageRoomStatus.UNCHECK;
    }
}
