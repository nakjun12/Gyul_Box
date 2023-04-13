package jeju.oneroom.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping
    public ResponseEntity<?> post(){
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{user-id}")
    public ResponseEntity<?> patch(@PathVariable("user-id") @Positive long Id){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{user-id}")
    public ResponseEntity<?> get(@PathVariable("user-id") @Positive long Id){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity<?> delete(){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}