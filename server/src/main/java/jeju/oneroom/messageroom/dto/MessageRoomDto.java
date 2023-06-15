package jeju.oneroom.messageroom.dto;

import jeju.oneroom.message.dto.MessageDto;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class MessageRoomDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post {

        @NotNull
        private Long senderId;

        @NotNull
        private Long receiverId;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private Long messageRoomId;
        private Long senderId;
        private String senderName;
        private Long receiverId;
        private String receiverName;
        private List<MessageDto.Response> messages;
        private LocalDateTime createdAt;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SimpleResponse {

        private Long messageRoomId;
        private String messageRoomStatus;
        private String lastMessage;
        private Long lastSenderId;
        private LocalDateTime createdAt;
    }
}
