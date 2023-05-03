package jeju.oneroom.houseInfo.controller;

import jeju.oneroom.area.entity.Area;
import jeju.oneroom.area.service.AreaService;
import jeju.oneroom.common.dto.ListResponseDto;
import jeju.oneroom.houseInfo.service.HouseInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class HouseInfoController {
    private final HouseInfoService houseInfoService;
    private final AreaService areaService;

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
    public ResponseEntity<?> getHouseInfo(@PathVariable("houseInfo-id") long houseInfoId) {
        return new ResponseEntity<>(houseInfoService.findHouseInfo(houseInfoId), HttpStatus.OK);
    }

    @GetMapping("/houseInfos/{houseInfo-id}/simple")
    public ResponseEntity<?> getHouseInfoSimple(@PathVariable("houseInfo-id") long houseInfoId) {
        return new ResponseEntity<>(houseInfoService.findHouseInfoSimple(houseInfoId), HttpStatus.OK);
    }

    // 동, 면, 웁에 속해있는 건물 좌표와 리뷰 수
    @GetMapping("areas/{area-id}/houseInfos")
    public ResponseEntity<?> getAreaHouseInfos(@PathVariable("area-id") long areaCode,
                                               @RequestParam int level) {
        Area area = areaService.findVerifiedAreaByAreaCode(areaCode);
        return new ResponseEntity<>(new ListResponseDto<>(houseInfoService.findAreaHouseInfos(area, level)), HttpStatus.OK);
    }
}
