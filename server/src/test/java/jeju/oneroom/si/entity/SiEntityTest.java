package jeju.oneroom.si.entity;

import jeju.oneroom.common.entity.Coordinate;
import jeju.oneroom.si.repository.SiRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(value = false)
class SiEntityTest {

    @Autowired
    SiRepository siRepository;

    @Test
    public void Si_생성_테스트() throws Exception{
        //given
        Coordinate coordinate = getCoordinate();
        Si si = getSi(coordinate);
        siRepository.save(si);

        //when
        Si findSi = siRepository.findById(si.getSiCode()).orElse(null);
        //then
        Assertions.assertEquals(findSi.getSiName(), si.getSiName());
    }

    private Coordinate getCoordinate() {
        Coordinate coordinate = new Coordinate(11.11111, 11.11111);
        return coordinate;
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