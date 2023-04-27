package jeju.oneroom.review.entity;

import jeju.oneroom.area.entity.Area;
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

    @Test
    public void Review_생성_테스트() throws Exception{
        //given
        Rate rate = getRate();
        Coordinate coordinate = new Coordinate(11.11111, 11.11111);
        Area area = getTownWithoutSi(coordinate);
        User user = getUser(area);
        userRepository.save(user);
        HouseInfo houseInfo = getHouseInfo(coordinate, rate, area);
        houseInfoRepository.save(houseInfo);

        Review review = getReview(user, rate, houseInfo);

        reviewRepository.save(review);
        //when
        Review findReview = reviewRepository.findById(review.getId()).orElse(null);
        //then
        Assertions.assertEquals(findReview.getBuildingName(), review.getBuildingName());
    }

    private Review getReview(User user, Rate rate, HouseInfo houseInfo) {
        return Review.builder()
                .buildingName("태성빌라")
                .adminCost("3000/50")
                .advantage("넓어요!")
                .disadvantage("드러워요!")
                .floor("중층")
                .residenceYear("약 2년")
                .address("인천 연수구 먼우금로")
                .likes(44)
                .user(user)
                .rate(rate)
                .houseInfo(houseInfo)
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
                .buildingRate(5)
                .interiorRate(5)
                .locationRate(5)
                .securityRate(5)
                .trafficRate(5)
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