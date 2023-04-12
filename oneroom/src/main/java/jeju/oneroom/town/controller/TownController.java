package jeju.oneroom.town.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TownController {
    @PostMapping
    public ResponseEntity<?> post(){
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/towns/{town-id}")
    public ResponseEntity<?> patch(@PathVariable("town-id") long townCode){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/towns/{town-id}")
    public ResponseEntity<?> delete(@PathVariable("town-id") long townCode){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/towns/{town-id}")
    public ResponseEntity<?> findTown(@PathVariable("town-id") long townCode){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("sies/{si-id}/towns")
    public ResponseEntity<?> findTownsBySi(@PathVariable("si-id") long siCode){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}