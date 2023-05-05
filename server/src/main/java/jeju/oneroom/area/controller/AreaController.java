package jeju.oneroom.area.controller;

import jeju.oneroom.area.dto.AreaDto;
import jeju.oneroom.area.entity.Area;
import jeju.oneroom.area.mapper.AreaMapper;
import jeju.oneroom.area.repository.AreaRepository;
import jeju.oneroom.area.service.AreaService;
import jeju.oneroom.common.dto.ListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AreaController {
    private final AreaService areaService;

    @PostMapping("/areas")
    public ResponseEntity<?> post() {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/areas/{area-id}")
    public ResponseEntity<?> patch(@PathVariable("area-id") long areaCode) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/areas/{area-id}")
    public ResponseEntity<?> delete(@PathVariable("area-id") long areaCode) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 프론트에서 동면읍, 시에 대한 정보를 데이터로 갖고 잊을건지?
    @GetMapping("/areas/search")
    public ResponseEntity<?> getArea(@RequestParam("name") String areaName) {
        return new ResponseEntity<>(new ListResponseDto<>(areaService.findAreasByAreaName(areaName)), HttpStatus.OK);
    }
}