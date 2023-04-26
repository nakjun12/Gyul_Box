package jeju.oneroom.user.entity;

import jeju.oneroom.area.entity.Area;
import jeju.oneroom.common.entity.Coordinate;
import jeju.oneroom.message.entity.Message;
import jeju.oneroom.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@Rollback(false)
class UserTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void 회원가입() throws Exception {


        //given
        User user = getUser(getTown(getCoordinate()),getMessage());

        //then
        User savedUser = userRepository.save(user); // db에 저장
        User findUser = userRepository.findById(savedUser.getId()).orElse(null); // db에서 id로 찾아옴

        assertEquals(user.getNickname(), findUser.getNickname()); // 직접 만든 User와 db에 저장 후 찾아온 것 비교
        assertEquals(user.getArea().getAreaName(), findUser.getArea().getAreaName());
        assertEquals(user.getSends().size(), findUser.getSends().size());

    }

    private User getUser(Area area, Message message) {
        List<Message> sends = new ArrayList<>();
        sends.add(message);
        User user = User.builder()
                .nickname("망나니 개발자")
                .email("aaa@naver.com")
                .area(area)
                .sends(sends)
                .build();
        return user;
    }

    private Area getTown(Coordinate coordinate) {
        Area area = Area.builder()
                .areaCode(11111L)
                .areaName("동춘동")
                .coordinate(coordinate)
                .build();
        return area;
    }

    private Coordinate getCoordinate() {
        Coordinate coordinate = new Coordinate(11.11111, 11.11111);
        return coordinate;
    }

    private Message getMessage() {
        return Message.builder()
                .content("양도때매 쪽지 드립니다.")
                .readed(Boolean.FALSE)
                .build();
    }
}