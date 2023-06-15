package jeju.oneroom.message.redis;

import jeju.oneroom.message.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisPublisher {

    private final RedisTemplate<String, Object> redisTemplate;

    public void publish(ChannelTopic topic, MessageDto.Response message) {
        try {
            redisTemplate.convertAndSend(topic.getTopic(), message);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        log.info("redis로 메시지 발행");
    }
}
