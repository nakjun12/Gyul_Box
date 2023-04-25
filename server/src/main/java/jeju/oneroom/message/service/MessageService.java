package jeju.oneroom.message.service;

import jeju.oneroom.message.entity.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MessageService {

    //메시지 생성
    public Message createMessage(Message message) {
        return message;
    }

    //메시지 삭제
    public void deleteMessage(Message message) {
    }

    // 메시지 읽음 유뮤 메서드
    public void readMessage(long sendId, long receiveId) {

    }
}
