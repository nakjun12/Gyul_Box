package jeju.oneroom.message.config.handler;

import jeju.oneroom.auth.service.JwtTokenizer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketHandler implements ChannelInterceptor {

    private final JwtTokenizer jwtTokenizer;

    // websocket 요청 시, 필터 역할
    @Override
    @CrossOrigin
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        if (StompCommand.CONNECT == accessor.getCommand()) {
            String jwt = Optional.ofNullable(accessor.getFirstNativeHeader("Authorization"))
                    .orElseThrow(() -> new RuntimeException("ACCESS_DENIED"))
                    .substring("Bearer ".length());

            jwtTokenizer.verifyAccessToken(jwt);
        }

        return message;
    }
}
