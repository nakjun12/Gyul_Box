package jeju.oneroom.area.service;

import jeju.oneroom.area.dto.AreaDto;
import jeju.oneroom.area.entity.Area;
import jeju.oneroom.area.mapper.AreaMapper;
import jeju.oneroom.area.repository.AreaRepository;
import jeju.oneroom.common.entity.Coordinate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AreaServiceTest {
    @Mock
    private AreaRepository areaRepository;

    @Mock
    private AreaMapper areaMapper;

    @InjectMocks
    private AreaService areaService;

    @Test
    @DisplayName("findAreasByAreaName 동작 확인")
    void 자역_이름으로_검색() {
        //given

        Area area1 = getArea_1();
        Area area2 = getArea_2();
        List<Area> areas = Arrays.asList(area1, area2);

        AreaDto.Response responseDto = getResponseDto(area1);

        //when
        when(areaRepository.findByAreaName(anyString())).thenReturn(areas);
        when(areaMapper.areaToResponseDto(any())).thenReturn(responseDto);

        List<AreaDto.Response> responses = areaService.findAreasByAreaName("동춘");

        //then
        assertEquals(area1.getAreaCode(), responses.get(0).getAreaCode());
    }

    @Test
    @DisplayName("findVerifiedAreaByAreaCode 동작 확인")
    void 자역_코드로_검색_성공() {
        //given
        Area area = getArea_1();

        //when
        when(areaRepository.findById(anyLong())).thenReturn(Optional.of(area));

        Area result= areaService.findVerifiedAreaByAreaCode(11111);

        //then
        assertEquals(area.getAreaCode(), result.getAreaCode());
    }

    @Test
    @DisplayName("findVerifiedAreaByAreaCode 동작 확인")
    void 자역_코드로_검색_실패() {
        //when
        when(areaRepository.findById(anyLong())).thenReturn(null);

        //then
        assertThrows(RuntimeException.class, () -> areaService.findVerifiedAreaByAreaCode(12345));
    }

    private AreaDto.Response getResponseDto(Area area1) {
        return AreaDto.Response.builder()
                .areaCode(area1.getAreaCode())
                .areaName(area1.getAreaName())
                .build();
    }

    private Area getArea_1() {
        Coordinate coordinate = new Coordinate(11.11111, 11.11111);
        return Area.builder()
                .areaCode(11111L)
                .areaName("동춘동")
                .coordinate(coordinate)
                .build();
    }

    private Area getArea_2() {
        Coordinate coordinate = new Coordinate(22.11111, 22.11111);
        return Area.builder()
                .areaCode(22222L)
                .areaName("논현동")
                .coordinate(coordinate)
                .build();
    }
}