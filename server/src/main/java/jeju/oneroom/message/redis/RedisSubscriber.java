package jeju.oneroom.message.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import jeju.oneroom.message.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisSubscriber implements MessageListener {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;
    private final SimpMessageSendingOperations messagingTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String publishMessage = String.valueOf(redisTemplate
                    .getStringSerializer()
                    .deserialize(message.getBody())
            );

            MessageDto.Response responseDto = objectMapper.readValue(publishMessage, MessageDto.Response.class);
            log.info("message = {}", responseDto);

            messagingTemplate.convertAndSend("/sub/room/" + responseDto.getMessageRoomId(), responseDto);
            log.info("redis에서 메시지 받아옴");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
