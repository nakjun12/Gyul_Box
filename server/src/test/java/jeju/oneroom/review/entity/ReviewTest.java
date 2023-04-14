package jeju.oneroom.review.entity;

import jeju.oneroom.common.entity.Rate;
import jeju.oneroom.review.repository.ReviewRepository;
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

    @Test
    public void Review_생성_테스트() throws Exception{
        //given
        Rate rate = getRate();

        Review review = Review.builder()
                .buildingName("태성빌라")
                .adminCost("3000/50")
                .advantage("넓어요!")
                .disadvantage("드러워요!")
                .floor("중층")
                .residenceYear("약 2년")
                .address("인천 연수구 먼우금로")
                .likes(1)
                .rate(rate)
                .build();

        reviewRepository.save(review);
        //when
        Review findReview = reviewRepository.findById(review.getId()).orElse(null);
        //then
        Assertions.assertEquals(findReview.getBuildingName(), review.getBuildingName());
    }

    private Rate getRate() {
        return Rate.builder()
                .avgRate(5)
                .buildingRate(5)
                .interiorRate(5)
                .locationRate(5)
                .securityRate(5)
                .trafficRate(5)
                .build();
    }


}