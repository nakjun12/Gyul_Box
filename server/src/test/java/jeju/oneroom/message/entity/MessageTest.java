package jeju.oneroom.message.entity;

import jeju.oneroom.message.repository.MessageRepository;
import jeju.oneroom.town.entity.Town;
import jeju.oneroom.user.entity.User;
import jeju.oneroom.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class MessageTest {

    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    public void 메세지_작성() throws Exception {

        //given
        User user = getUser();
        User savedUser = userRepository.save(user);

        User user2 = getUser();
        User savedUser2 = userRepository.save(user2);
        Message message = getMessage(savedUser, savedUser2);

        //then
        Message savedMessage = messageRepository.save(message); // db에 저장
        Message findMessage = messageRepository.findById(savedMessage.getId()).orElse(null); // db에서 id로 찾아옴

        assertEquals(message.getContent(), findMessage.getContent()); // 직접 만든 Message와 db에 저장 후 찾아온 것 비교
    }

    private Message getMessage(User user, User user2) {
        return Message.builder()
                .content("양도때매 쪽지 드립니다.")
                .receiver(user)
                .sender(user2)
                .readed(Boolean.FALSE)
                .build();
    }

    private User getUser() {
        User user = User.builder()
                .nickname("망나니 개발자")
                .email("aaa@naver.com")
                .build();
        return user;
    }
}