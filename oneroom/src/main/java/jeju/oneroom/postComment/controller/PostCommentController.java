package jeju.oneroom.postComment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/posts/{post-id}/postComments")
public class PostCommentController {

    @PostMapping
    public ResponseEntity post(@PathVariable("post-id") @Positive long postId){
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{postComment-id}")
    public ResponseEntity patch(@PathVariable("post-id") @Positive long postId, @PathVariable("postComment-id") @Positive long postCommentId){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{postComment-id}")
    public ResponseEntity find(@PathVariable("post-id") @Positive long postId, @PathVariable("postComment-id") @Positive long postCommentId){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity finds(@PathVariable("post-id") @Positive long postId){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{postComment-id}")
    public ResponseEntity delete(@PathVariable("post-id") @Positive long postId, @PathVariable("postComment-id") @Positive long postCommentId){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}