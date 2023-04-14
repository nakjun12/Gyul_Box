package jeju.oneroom.user.entity;

import jeju.oneroom.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void 회원가입() throws Exception {

        //given
        User user = getUser();

        //then
        User savedUser = userRepository.save(user); // db에 저장
        User findUser = userRepository.findById(savedUser.getId()).orElse(null); // db에서 id로 찾아옴

        assertEquals(user.getNickname(), findUser.getNickname()); // 직접 만든 User와 db에 저장 후 찾아온 것 비교
    }

    private User getUser() {
        User user = User.builder()
                .nickname("망나니 개발자")
                .email("aaa@naver.com")
                .build();
        return user;
    }
}