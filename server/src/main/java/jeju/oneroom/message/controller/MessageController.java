package jeju.oneroom.message.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@RestController
@RequestMapping("users/{user-id}/messages")
public class MessageController {

    // 메세지 보내기
    @PostMapping
    public ResponseEntity post(@PathVariable("user-id") @Positive long userId){
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    @PatchMapping("/{message-id}")
//    public ResponseEntity patch(@PathVariable("message-id") @Positive long Id){
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @GetMapping("/{message-id}")
    public ResponseEntity get(@PathVariable("message-id") @Positive long Id){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{message-id}")
    public ResponseEntity delete(){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
