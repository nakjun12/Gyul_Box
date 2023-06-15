package jeju.oneroom.message.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class MessageDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post {

        @NotNull
        private Long messageRoomId;

        @NotNull
        private Long senderId;  // email

        @NotNull
        private Long receiverId;

        @NotNull
        private String content;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private Long messageId;
        private String content;
        private Long messageRoomId;
        private Long senderId;
        private String senderName;
        private Long receiverId;
        private String receiverName;

        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        private LocalDateTime createdAt;
    }
}
