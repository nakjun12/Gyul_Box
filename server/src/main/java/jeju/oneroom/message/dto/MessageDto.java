package jeju.oneroom.message.dto;

import jeju.oneroom.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MessageDto {

    @Getter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class Post{
        private Long senderId;
        private Long receiveId;
        private String content;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class Response{
        private Long id; // messageId
        private String content; // 내용
        private LocalDateTime createdAt; // 보낸시간
        private UserDto.SimpleResponse user;
    }
}

