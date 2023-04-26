package jeju.oneroom.houseInfo.controller;

import jeju.oneroom.area.entity.Area;
import jeju.oneroom.area.repository.AreaRepository;
import jeju.oneroom.common.dto.ListResponseDto;
import jeju.oneroom.houseInfo.dto.HouseInfoDto;
import jeju.oneroom.houseInfo.entity.HouseInfo;
import jeju.oneroom.houseInfo.mapper.HouseInfoMapper;
import jeju.oneroom.houseInfo.repository.HouseInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class HouseInfoController {
    private final HouseInfoRepository houseInfoRepository;
    private final HouseInfoMapper houseInfoMapper;
    private final AreaRepository areaRepository;

    @PostMapping("/houseInfos")
    public ResponseEntity<?> post() {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/houseInfos/{houseInfo-id}")
    public ResponseEntity<?> patch(@PathVariable("houseInfo-id") long houseInfoId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/houseInfos/{houseInfo-id}")
    public ResponseEntity<?> delete(@PathVariable("houseInfo-id") long houseInfoId) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/houseInfos/{houseInfo-id}")
    public ResponseEntity<?> findHouseInfo(@PathVariable("houseInfo-id") long houseInfoId) {
        HouseInfo houseInfo = houseInfoRepository.findById(houseInfoId).orElse(null);
        HouseInfoDto.Response response = houseInfoMapper.houseInfoToResponseDto(houseInfo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/houseInfos/{houseInfo-id}/simple")
    public ResponseEntity<?> findHouseInfoSimple(@PathVariable("houseInfo-id") long houseInfoId) {
        HouseInfo houseInfo = houseInfoRepository.findById(houseInfoId).orElse(null);
        HouseInfoDto.SimpleResponse response = houseInfoMapper.houseInfoToSimpleResponseDto(houseInfo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 동, 면, 웁에 속해있는 건물 좌표와 리뷰 수
    @GetMapping("areas/{area-id}/houseInfos")
    public ResponseEntity<?> findAreaHouseInfos(@PathVariable("area-id") long areaCode,
                                                @RequestParam int level) {
        Area area = areaRepository.findById(areaCode).orElse(null);
        List<HouseInfoDto.SimpleCountResponse> responses;
        if (level >= 9){
            responses = houseInfoRepository.findTop20ByAreaOrderByReviewCount(area).stream().map(houseInfoMapper::houseInfoToSimpleCountResponseDto).collect(Collectors.toList());
        }else {
            responses = houseInfoRepository.findByArea(area).stream().map(houseInfoMapper::houseInfoToSimpleCountResponseDto).collect(Collectors.toList());
        }
        return new ResponseEntity<>(new ListResponseDto<>(responses), HttpStatus.OK);
    }
}
