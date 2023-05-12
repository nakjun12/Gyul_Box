package jeju.oneroom.houseInfo.service;

import jeju.oneroom.area.entity.Area;
import jeju.oneroom.common.entity.Coordinate;
import jeju.oneroom.common.entity.Rate;
import jeju.oneroom.houseInfo.dto.HouseInfoDto;
import jeju.oneroom.houseInfo.entity.HouseInfo;
import jeju.oneroom.houseInfo.mapper.HouseInfoMapper;
import jeju.oneroom.houseInfo.repository.HouseInfoRepository;
import jeju.oneroom.review.dto.ReviewDto;
import jeju.oneroom.review.entity.Review;
import jeju.oneroom.user.dto.UserDto;
import jeju.oneroom.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Slf4j
class HouseInfoServiceTest {
    @Mock
    HouseInfoRepository houseInfoRepository;

    @Mock
    HouseInfoMapper houseInfoMapper;

    @InjectMocks
    HouseInfoService houseInfoService;

    @Test
    @DisplayName("findHouseInfo 성공")
    void 건물_Id로_모두_조회_성공() {
        HouseInfo houseInfo = getHouseInfo(getRate(), getArea(), List.of(getReview(getUser(getArea()), getRate())));
        HouseInfoDto.Response response = getHouseInfoResponse(houseInfo);

        when(houseInfoRepository.findById(anyLong())).thenReturn(Optional.of(houseInfo));
        when(houseInfoMapper.houseInfoToResponseDto(any(HouseInfo.class))).thenReturn(response);

        HouseInfoDto.Response result = houseInfoService.findHouseInfo(1L);
        assertEquals(houseInfo.getHouseName(),result.getHouseName());
    }

    @Test
    @DisplayName("findHouseInfo 실패")
    void 건물_Id로_모두_조회_실패() {
        when(houseInfoRepository.findById(anyLong())).thenReturn(null);

        assertThrows(RuntimeException.class, () -> houseInfoService.findHouseInfo(1L));
    }

    @Test
    @DisplayName("findHouseInfoSimple 성공")
    void 건물_Id로_단순_조회_성공() {
        HouseInfo houseInfo = getHouseInfo(getRate(), getArea(), List.of(getReview(getUser(getArea()), getRate())));
        HouseInfoDto.SimpleResponse SimpleResponse = getHouseInfoSimpleResponse();

        when(houseInfoRepository.findById(anyLong())).thenReturn(Optional.of(houseInfo));
        when(houseInfoMapper.houseInfoToSimpleResponseDto(any(HouseInfo.class))).thenReturn(SimpleResponse);

        HouseInfoDto.SimpleResponse result = houseInfoService.findHouseInfoSimple(1L);
        assertEquals(houseInfo.getHouseName(),result.getHouseName());
    }

    @Test
    @DisplayName("findHouseInfoSimple 실패")
    void 건물_Id로_단순_조회_실패() {
        when(houseInfoRepository.findById(anyLong())).thenReturn(null);

        assertThrows(RuntimeException.class, () -> houseInfoService.findHouseInfoSimple(1L));
    }

    @Test
    @DisplayName("findHouseInfosByArea level>=9")
    void AreaCode로_다중_조회_레벨_9_이상() {
        HouseInfo houseInfo = getHouseInfo(getRate(), getArea(), List.of(getReview(getUser(getArea()), getRate())));
        List<HouseInfo> houseInfos = List.of(houseInfo);
        HouseInfoDto.SimpleCountResponse SimpleCountResponse = getHouseInfoSimpleCountResponse();

        when(houseInfoRepository.findTop20ByAreaOrderByReviewCount(any(Area.class))).thenReturn(houseInfos);
        when(houseInfoMapper.houseInfoToSimpleCountResponseDto(any(HouseInfo.class))).thenReturn(SimpleCountResponse);

        List<HouseInfoDto.SimpleCountResponse> result = houseInfoService.findHouseInfosByArea(getArea(), 9);
        assertEquals(houseInfo.getCoordinate().getLatitude(),result.get(0).getCoordinate().getLatitude());
        assertEquals(houseInfo.getCoordinate().getLongitude(),result.get(0).getCoordinate().getLongitude());
    }

    @Test
    @DisplayName("findHouseInfosByArea level<9")
    void AreaCode로_다중_조회_레벨_9_미만() {
        HouseInfo houseInfo = getHouseInfo(getRate(), getArea(), List.of(getReview(getUser(getArea()), getRate())));
        List<HouseInfo> houseInfos = List.of(houseInfo);
        HouseInfoDto.SimpleCountResponse SimpleCountResponse = getHouseInfoSimpleCountResponse();

        when(houseInfoRepository.findByArea(any(Area.class))).thenReturn(houseInfos);
        when(houseInfoMapper.houseInfoToSimpleCountResponseDto(any(HouseInfo.class))).thenReturn(SimpleCountResponse);

        List<HouseInfoDto.SimpleCountResponse> result = houseInfoService.findHouseInfosByArea(getArea(), 8);
        assertEquals(houseInfo.getCoordinate().getLatitude(),result.get(0).getCoordinate().getLatitude());
        assertEquals(houseInfo.getCoordinate().getLongitude(),result.get(0).getCoordinate().getLongitude());
    }

    @Test
    @DisplayName("findHouseInfosByContent 조회")
    void Content로_다중_조회() {
        HouseInfo houseInfo = getHouseInfo(getRate(), getArea(), List.of(getReview(getUser(getArea()), getRate())));
        List<HouseInfo> houseInfos = List.of(houseInfo);
        HouseInfoDto.SimpleContentResponse SimpleContentResponse = getHouseInfoSimpleContentRespnse();

        when(houseInfoRepository.findByPlatPlcContains(anyString())).thenReturn(houseInfos);
        when(houseInfoMapper.houseInfoToSimpleContentResponseDto(any(HouseInfo.class))).thenReturn(SimpleContentResponse);

        List<HouseInfoDto.SimpleContentResponse> result = houseInfoService.findHouseInfosByContent("인천");
        assertEquals(houseInfo.getHouseName(),result.get(0).getHouseName());
    }

    @Test
    @DisplayName("updateHouseInfoRate 자동수정")
    void HouseInfo_Rate_자동수정() {
        HouseInfo houseInfo = getHouseInfo(getRate(), getArea(), List.of(getReview(getUser(getArea()), getRate())));
        Rate newRate = Rate.builder()
                .buildingRate(0)
                .interiorRate(0)
                .locationRate(0)
                .securityRate(0)
                .trafficRate(0)
                .build();
        houseInfoService.updateHouseInfoRate(houseInfo,newRate);
        assertEquals(houseInfo.getRate().getAvgRate(), 2.5);
    }

    @Test
    void findHouseInfosByArea() {
    }

    @Test
    void findHouseInfosByContent() {
    }

    @Test
    void updateHouseInfoRate() {
    }

    @Test
    void findVerifiedHouseInfo() {
    }

    private HouseInfo getHouseInfo(Rate rate, Area area, List<Review> reviews) {
        Coordinate coordinate = new Coordinate(22.11111, 22.11111);
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
                .rate(rate)
                .coordinate(coordinate)
                .area(area)
                .reviews(reviews)
                .build();
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

    private Area getArea() {
        Coordinate coordinate = new Coordinate(22.11111, 22.11111);
        Area area = Area.builder()
                .areaCode(11111L)
                .areaName("동춘동")
                .coordinate(coordinate)
                .build();
        return area;
    }

    private Review getReview(User user, Rate rate) {
        return Review.builder()
                .buildingName("태성빌라")
                .adminCost("3000/50")
                .advantage("넓어요!")
                .disadvantage("드러워요!")
                .floor("중층")
                .residenceYear("약 2년")
                .address("인천 연수구 먼우금로")
                .user(user)
                .rate(rate)
                .build();
    }

    private User getUser(Area area) {
        return User.builder()
                .email("aaa@gmail.com")
                .area(area)
                .nickname("홍길동").build();
    }

    private HouseInfoDto.Response getHouseInfoResponse(HouseInfo houseInfo) {
        return HouseInfoDto.Response.builder()
                .houseHold(houseInfo.getHouseHold())
                .buildingStructure(houseInfo.getBuildingStructure())
                .buildUes(houseInfo.getBuildUes())
                .coordinate(houseInfo.getCoordinate())
                .elevator(houseInfo.getElevator())
                .houseName(houseInfo.getHouseName())
                .floor("지상 " + houseInfo.getGrndFloor() + "층 (지하 " + houseInfo.getUgrndFloor() + "층)")
                .rate(houseInfo.getRate())
                .platPlc(houseInfo.getPlatPlc())
                .useAprDay(houseInfo.getUseAprDay())
                .reviews(List.of(getReviewResponse())).build();
    }

    private ReviewDto.Response getReviewResponse() {
        return ReviewDto.Response.builder()
                .adminCost("3만원")
                .advantage("장점")
                .disadvantage("단점")
                .avgRate(5)
                .buildingName("태성빌라")
                .floor("중층")
                .reviewId(1L)
                .residenceYear("약 3년")
                .likes(2)
                .writer(getUserSimpleResponse()).build();
    }

    private UserDto.SimpleResponse getUserSimpleResponse() {
        return UserDto.SimpleResponse.builder()
                .id(1L)
                .nickname("킴")
                .profileImageUrl("localhost")
                .build();
    }

    private HouseInfoDto.SimpleResponse getHouseInfoSimpleResponse() {
        return HouseInfoDto.SimpleResponse.builder()
                .avgRate(5)
                .houseName("태성빌라")
                .platPlc("인천 연수구")
                .reviewCount(2)
                .id(1L).build();
    }

    private HouseInfoDto.SimpleCountResponse getHouseInfoSimpleCountResponse() {
        return HouseInfoDto.SimpleCountResponse.builder()
                .id(1L)
                .reviewCount(2)
                .coordinate(new Coordinate(22.11111, 22.11111))
                .build();
    }

    private HouseInfoDto.SimpleContentResponse getHouseInfoSimpleContentRespnse() {
        return HouseInfoDto.SimpleContentResponse.builder()
                .id(1L)
                .platPlc("인천 연수구")
                .houseName("태성빌라")
                .build();
    }
}