package jeju.oneroom.area.controller;

import jeju.oneroom.area.dto.AreaDto;
import jeju.oneroom.area.entity.Area;
import jeju.oneroom.area.mapper.AreaMapper;
import jeju.oneroom.area.repository.AreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AreaController {
    private final AreaRepository areaRepository;
    private final AreaMapper areaMapper;

    @PostMapping("/areas")
    public ResponseEntity<?> post() {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/areas/{area-id}")
    public ResponseEntity<?> patch(@PathVariable("town-id") long areaCode) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/areas/{area-id}")
    public ResponseEntity<?> delete(@PathVariable("town-id") long areaCode) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/areas/{area-id}")
    public ResponseEntity<?> findTown(@PathVariable("town-id") long areaCode) {
        Area area = areaRepository.findById(areaCode).orElse(null);
        AreaDto.Response response = areaMapper.areaToResponseDto(area);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}