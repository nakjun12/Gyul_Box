package jeju.oneroom.town.entity;

import jeju.oneroom.common.entity.Coordinate;
import jeju.oneroom.si.entity.Si;
import jeju.oneroom.town.repository.TownRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(value = false)
class TownTest {

    @Autowired
    TownRepository townRepository;

    @Test
    public void Town_생성_테스트() throws Exception{
        //given
        Coordinate coordinate = getCoordinate();
        Si si = getSi(coordinate);
        Town town = getTown(coordinate, si);

        townRepository.save(town);

        //when
        Town findTown = townRepository.findById(town.getTownCode()).orElse(null);
        //then
        Assertions.assertEquals(findTown.getTownName(), town.getTownName());
    }

    private Coordinate getCoordinate() {
        Coordinate coordinate = new Coordinate(11.11111, 11.11111);
        return coordinate;
    }

    private Town getTownWithoutSi(Coordinate coordinate) {
        Town town = Town.builder()
                .townCode(11111L)
                .townName("동춘동")
                .coordinate(coordinate)
                .build();
        return town;
    }

    private Town getTown(Coordinate coordinate, Si si) {
        Town town = Town.builder()
                .townCode(11111L)
                .townName("동춘동")
                .si(si)
                .coordinate(coordinate)
                .build();
        return town;
    }

    private Si getSi(Coordinate coordinate) {
        Si si = Si.builder()
                .siCode(11111L)
                .siName("인천")
                .coordinate(coordinate)
                .build();
        return si;
    }
}