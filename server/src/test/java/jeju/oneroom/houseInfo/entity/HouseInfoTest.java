package jeju.oneroom.houseInfo.entity;

import jeju.oneroom.area.entity.Area;
import jeju.oneroom.area.repository.AreaRepository;
import jeju.oneroom.common.entity.Coordinate;
import jeju.oneroom.common.entity.Rate;
import jeju.oneroom.houseInfo.entity.HouseInfo;
import jeju.oneroom.houseInfo.repository.HouseInfoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
@Rollback(value = false)
class HouseInfoTest {

    @Autowired
    HouseInfoRepository houseInfoRepository;

    @Autowired
    AreaRepository areaRepository;

//    @Test
//    public void HouseInfo_생성_테스트() throws Exception{
//        Area area = areaRepository.findById(5013031000L).orElse(null);
//        List<HouseInfo> houseInfos = houseInfoRepository.findByPlatPlcContains(area.getAreaName());
//        for (HouseInfo houseInfo : houseInfos) {
//            houseInfo.setArea(area);
//        }
//    }

    @Test
    public void HouseInfo_생성_테스트2() throws Exception{
        //given
        Coordinate coordinate = new Coordinate(21.11111, 21.11111);
        Rate rate = getRate();
        Area area = areaRepository.findById(11111L).orElse(null);
        HouseInfo houseInfo = getHouseInfo(coordinate, area);

        houseInfoRepository.save(houseInfo);

//        //when
//        HouseInfo findHouseInfo = houseInfoRepository.findById(houseInfo.getId()).orElse(null);
//        //then
//        Assertions.assertEquals(findHouseInfo.getHouseName(), houseInfo.getHouseName());
    }

    private HouseInfo getHouseInfo(Coordinate coordinate, Area area) {
        return HouseInfo.builder()
                .mainPurpsCdNm("주거")
                .houseHold(1)
                .houseName(null)
                .buildingStructure("콘크리트")
                .buildUes("주거")
                .elevator(1)
                .platPlc("인천 연수구 먼우금로 113")
                .grndFloor(5)
                .ugrndFloor(2)
                .useAprDay("2002-02-02")
//                .rate(rate)
                .coordinate(coordinate)
                .area(area)
                .build();
    }

    private HouseInfo getHouseInfoWithoutTown(Coordinate coordinate, Rate rate) {
        return HouseInfo.builder()
                .mainPurpsCdNm("주거")
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
                .build();
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
}