package jeju.oneroom.si.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sies")
public class SiController {
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
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findSies() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
