package jeju.oneroom.si.controller;

import jeju.oneroom.common.dto.ListResponseDto;
import jeju.oneroom.si.dto.SiDto;
import jeju.oneroom.si.entity.Si;
import jeju.oneroom.si.mapper.SiMapper;
import jeju.oneroom.si.repository.SiRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sies")
@Slf4j
public class SiController {

    private final SiRepository siRepository;
    private final SiMapper siMapper;

    @PostMapping
    public ResponseEntity<?> post() {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{si-id}")
    public ResponseEntity<?> patch(@PathVariable("si-id") long siCode) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{si-id}")
    public ResponseEntity<?> delete(@PathVariable("si-id") long siCode) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{si-id}")
    public ResponseEntity<?> findSi(@PathVariable("si-id") long siCode) {
        Si si = siRepository.findById(siCode).orElse(null);
        SiDto.Response response = siMapper.siToResponseDto(si);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findSies() {
        List<SiDto.Response> responses = siRepository.findAll().stream().map(siMapper::siToResponseDto).collect(Collectors.toList());
        return new ResponseEntity<>(new ListResponseDto<>(responses), HttpStatus.OK);
    }
}
