package jeju.oneroom.area.controller;

import jeju.oneroom.area.service.AreaService;
import jeju.oneroom.common.dto.ListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AreaController {
    private final AreaService areaService;

    @GetMapping("/areas/search")
    public ResponseEntity<?> getArea(@RequestParam("name") String areaName) {
        return new ResponseEntity<>(new ListResponseDto<>(areaService.findAreasByAreaName(areaName)), HttpStatus.OK);
    }
}