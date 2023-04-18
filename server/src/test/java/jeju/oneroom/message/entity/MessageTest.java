package jeju.oneroom.message.entity;

import jeju.oneroom.message.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class MessageTest {

    @Autowired
    MessageRepository messageRepository;

    @Test
    public void 메세지_작성() throws Exception {

        //given
        Message message = getMessage();

        //then
        Message savedMessage = messageRepository.save(message); // db에 저장
        Message findMessage = messageRepository.findById(savedMessage.getId()).orElse(null); // db에서 id로 찾아옴

        assertEquals(message.getContent(), findMessage.getContent()); // 직접 만든 Message와 db에 저장 후 찾아온 것 비교
    }

    private Message getMessage() {
        return Message.builder()
                .content("양도때매 쪽지 드립니다.")
                .readed(Boolean.FALSE)
                .build();
    }
}