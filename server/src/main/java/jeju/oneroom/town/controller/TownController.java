package jeju.oneroom.town.controller;

import jeju.oneroom.common.dto.ListResponseDto;
import jeju.oneroom.common.dto.MultiResponseDto;
import jeju.oneroom.si.dto.SiDto;
import jeju.oneroom.si.entity.Si;
import jeju.oneroom.si.repository.SiRepository;
import jeju.oneroom.town.dto.TownDto;
import jeju.oneroom.town.entity.Town;
import jeju.oneroom.town.mapper.TownMapper;
import jeju.oneroom.town.repository.TownRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TownController {
    private final TownRepository townRepository;
    private final TownMapper townMapper;
    private final SiRepository siRepository;

    @PostMapping("/towns")
    public ResponseEntity<?> post() {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/towns/{town-id}")
    public ResponseEntity<?> patch(@PathVariable("town-id") long townCode) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/towns/{town-id}")
    public ResponseEntity<?> delete(@PathVariable("town-id") long townCode) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/towns/{town-id}")
    public ResponseEntity<?> findTown(@PathVariable("town-id") long townCode) {
        Town town = townRepository.findById(townCode).orElse(null);
        TownDto.Response response = townMapper.townToResponseDto(town);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("sies/{si-id}/towns")
    public ResponseEntity<?> findTownsBySi(@PathVariable("si-id") long siCode) {
        Si si = siRepository.findById(siCode).orElse(null);
        List<TownDto.Response> responses = townRepository.findBySi(si).stream().map(townMapper ::townToResponseDto).collect(Collectors.toList());
        return new ResponseEntity<>(new ListResponseDto<>(responses),HttpStatus.OK);
    }
}