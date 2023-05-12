package jeju.oneroom.user.entity;

import jeju.oneroom.area.entity.Area;
import jeju.oneroom.common.entity.Coordinate;
import jeju.oneroom.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

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
        User user = getUser(getArea(getCoordinate()));

        //then
        User savedUser = userRepository.save(user); // db에 저장
        User findUser = userRepository.findById(savedUser.getId()).orElse(null); // db에서 id로 찾아옴

        assertEquals(user.getNickname(), findUser.getNickname()); // 직접 만든 User와 db에 저장 후 찾아온 것 비교
        assertEquals(user.getArea().getAreaName(), findUser.getArea().getAreaName());

    }

    private User getUser(Area area) {
        User user = User.builder()
                .nickname("망나니DD 개발자")
                .email("aaSDDa@naver.com")
                .area(area)
                .build();
        return user;
    }

    private Area getArea(Coordinate coordinate) {
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
}