package jeju.oneroom.houseInfo.entity;

import jeju.oneroom.common.entity.Coordinate;
import jeju.oneroom.common.entity.Rate;
import jeju.oneroom.houseInfo.repository.HouseInfoRepository;
import jeju.oneroom.town.entity.Town;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(value = false)
class HouseInfoTest {

    @Autowired
    HouseInfoRepository houseInfoRepository;

    @Test
    public void HouseInfo_생성_테스트() throws Exception{
        //given
        Coordinate coordinate = new Coordinate(11.11111, 11.11111);
        Rate rate = getRate();
        Town town = getTownWithoutSi(coordinate);
        HouseInfo houseInfo = getHouseInfo(coordinate, rate, town);


        houseInfoRepository.save(houseInfo);

        //when
        HouseInfo findHouseInfo = houseInfoRepository.findById(houseInfo.getId()).orElse(null);
        //then
        Assertions.assertEquals(findHouseInfo.getHouseName(), houseInfo.getHouseName());
    }

    private HouseInfo getHouseInfo(Coordinate coordinate, Rate rate, Town town) {
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
                .town(town)
                .build();
    }

    private HouseInfo getHouseInfoWithoutTown(Coordinate coordinate, Rate rate) {
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

    private Town getTownWithoutSi(Coordinate coordinate) {
        Town town = Town.builder()
                .townCode(11111L)
                .townName("동춘동")
                .coordinate(coordinate)
                .build();
        return town;
    }
}