package jeju.oneroom.area.entity;

import jeju.oneroom.area.repository.AreaRepository;
import jeju.oneroom.common.entity.Coordinate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(value = false)
class AreaTest {

    @Autowired
    AreaRepository areaRepository;

    @Test
    public void Area_생성_테스트() throws Exception{
        //given
        Coordinate coordinate = getCoordinate();
        Area area = setArea(coordinate);

        areaRepository.save(area);
    }

    private Coordinate getCoordinate() {
        Coordinate coordinate = new Coordinate(21.11111, 21.11111);
        return coordinate;
    }

    private Area setArea(Coordinate coordinate) {
        Area area = Area.builder()
                .areaCode(11111L)
                .areaName("동춘동1")
                .coordinate(coordinate)
                .build();
        return area;
    }
}