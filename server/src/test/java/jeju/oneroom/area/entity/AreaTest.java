package jeju.oneroom.area.entity;

import jeju.oneroom.area.entity.Area;
import jeju.oneroom.area.repository.AreaRepository;
import jeju.oneroom.common.entity.Coordinate;
import org.junit.jupiter.api.Assertions;
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
    public void Town_생성_테스트() throws Exception{
        //given
        Coordinate coordinate = getCoordinate();
        Area area = getTown(coordinate);

        areaRepository.save(area);

        //when
        Area findTown = areaRepository.findById(area.getTownCode()).orElse(null);
        //then
        Assertions.assertEquals(findTown.getTownName(), area.getTownName());
    }

    private Coordinate getCoordinate() {
        Coordinate coordinate = new Coordinate(11.11111, 11.11111);
        return coordinate;
    }

    private Area getTownWithoutSi(Coordinate coordinate) {
        Area town = Area.builder()
                .townCode(11111L)
                .townName("동춘동")
                .coordinate(coordinate)
                .build();
        return town;
    }

    private Area getTown(Coordinate coordinate) {
        Area area = Area.builder()
                .townCode(11111L)
                .townName("동춘동")
                .coordinate(coordinate)
                .build();
        return area;
    }
}