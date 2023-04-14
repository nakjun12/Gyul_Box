package jeju.oneroom.message.entity;

import jeju.oneroom.user.entity.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Message {
    @Id
    @GeneratedValue
    @Column(name = "message_id")
    private Long id;

    private String content;
    private Boolean readed;

    @CreatedDate
    private LocalDateTime dispatchTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User receiver;

    @Builder
    public Message(String content, Boolean readed, LocalDateTime dispatchTime, User sender, User receiver) {
        this.content = content;
        this.readed = readed;
        this.dispatchTime = dispatchTime;
        this.sender = sender;
        this.receiver = receiver;
    }
}
