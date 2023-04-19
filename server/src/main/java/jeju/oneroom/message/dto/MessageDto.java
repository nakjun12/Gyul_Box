package jeju.oneroom.message.dto;

import jeju.oneroom.user.dto.UserDto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
public class MessageDto {
    @Getter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class Post {
        private String content; // 내용
        private Long sender_id; // 보낸사람
        private Long receiver_id; // 받는 사람
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class Response {
        private Long id; // messageId
        private String content; // 내용
        private UserDto.SimpleResponseDto receiver; // 받는 사람
        private LocalDateTime createdAt; // 보낸시간
    }
}
