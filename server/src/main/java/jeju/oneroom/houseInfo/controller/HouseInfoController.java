package jeju.oneroom.houseInfo.controller;

import jeju.oneroom.common.dto.ListResponseDto;
import jeju.oneroom.houseInfo.dto.HouseInfoDto;
import jeju.oneroom.houseInfo.entity.HouseInfo;
import jeju.oneroom.houseInfo.mapper.HouseInfoMapper;
import jeju.oneroom.houseInfo.repository.HouseInfoRepository;
import jeju.oneroom.town.entity.Town;
import jeju.oneroom.town.repository.TownRepository;
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
    private final TownRepository townRepository;

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

    @GetMapping("/houseInfos/{houseInfo-id}/count")
    public ResponseEntity<?> findHouseInfoCount(@PathVariable("houseInfo-id") long houseInfoId) {
        HouseInfo houseInfo = houseInfoRepository.findById(houseInfoId).orElse(null);
        HouseInfoDto.SimpleResponse response = houseInfoMapper.houseInfoToSimpleResponseDto(houseInfo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 동, 면, 웁에 속해있는 건물 좌표와 리뷰 수
    @GetMapping("towns/{town-id}/houseInfos")
    public ResponseEntity<?> findTownHouseInfos(@PathVariable("town-id") long townCode) {
        Town town = townRepository.findById(townCode).orElse(null);
        List<HouseInfoDto.SimpleCountResponse> responses = houseInfoRepository.findByTown(town).stream().map(houseInfoMapper::houseInfoToSimpleCountResponseDto).collect(Collectors.toList());
        return new ResponseEntity<>(new ListResponseDto<>(responses), HttpStatus.OK);
    }
}
