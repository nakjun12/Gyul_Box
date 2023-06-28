package jeju.oneroom.review.entity;

import jeju.oneroom.area.entity.Area;
import jeju.oneroom.area.repository.AreaRepository;
import jeju.oneroom.common.entity.Coordinate;
import jeju.oneroom.common.entity.Rate;
import jeju.oneroom.houseInfo.entity.HouseInfo;
import jeju.oneroom.houseInfo.repository.HouseInfoRepository;
import jeju.oneroom.review.entity.Review;
import jeju.oneroom.review.repository.ReviewRepository;
import jeju.oneroom.user.entity.User;
import jeju.oneroom.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
@Rollback(value = false)
class ReviewTest {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    HouseInfoRepository houseInfoRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AreaRepository areaRepository;

    @Test
    public void Review_생성_테스트() throws Exception{
        //given
        for (int i = 0; i < 10000; i++) {
            Rate rate = getRate();
            Coordinate coordinate = new Coordinate(11.11111, 11.11111);
            Area area = areaRepository.findById(11111L).orElse(null);
            User user = userRepository.findById((long) i).orElse(null);
            HouseInfo houseInfo = houseInfoRepository.findById(2L).orElse(null);

            Review review = getReview(user, rate, houseInfo);

            reviewRepository.save(review);
        }

//        //when
//        Review findReview = reviewRepository.findById(review.getId()).orElse(null);
//        //then
//        Assertions.assertEquals(findReview.getBuildingName(), review.getBuildingName());
    }

    private Review getReview(User user, Rate rate, HouseInfo houseInfo) {
        return Review.builder()
                .buildingName("태성빌라")
                .adminCost("3000/50 입니다~~~~~")
                .advantage("넓어요!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
                .disadvantage("드러워요!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
                .floor("중층")
                .residenceYear("약 2년")
                .user(user)
                .rate(rate)
                .houseInfo(houseInfo)
                .buildingType("오피스텔")
                .build();
    }

    private User getUser(Area area) {
        return User.builder()
                .email("aaa@gmail.com")
                .area(area)
                .nickname("홍길동").build();
    }

    private Rate getRate() {
        return Rate.builder()
                .buildingRate(5.0)
                .interiorRate(5.0)
                .locationRate(5.0)
                .securityRate(5.0)
                .trafficRate(5.0)
                .build();
    }

    private Area getTownWithoutSi(Coordinate coordinate) {
        Area area = Area.builder()
                .areaCode(11111L)
                .areaName("동춘동")
                .coordinate(coordinate)
                .build();
        return area;
    }

    private HouseInfo getHouseInfo(Coordinate coordinate, Rate rate, Area area) {
        return HouseInfo.builder()
                .houseHold(1)
                .houseName("태성빌라")
                .buildingStructure("콘크리트")
                .buildUes("주거")
                .elevator(1)
                .platPlc("인천 연수구 먼우금로")
                .grndFloor(5)
                .ugrndFloor(2)
                .useAprDay("2002-02-02")
                .area(area)
                .rate(rate)
                .coordinate(coordinate)
                .build();
    }


}