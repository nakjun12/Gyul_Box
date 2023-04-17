package jeju.oneroom.town.controller;

import jeju.oneroom.si.dto.SiDto;
import jeju.oneroom.si.entity.Si;
import jeju.oneroom.town.dto.TownDto;
import jeju.oneroom.town.entity.Town;
import jeju.oneroom.town.mapper.TownMapper;
import jeju.oneroom.town.repository.TownRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TownController {
    private final TownRepository townRepository;
    private final TownMapper townMapper;

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
        return new ResponseEntity<>(HttpStatus.OK);
    }
}