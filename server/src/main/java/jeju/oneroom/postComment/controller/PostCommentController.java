package jeju.oneroom.postComment.controller;

import jeju.oneroom.postComment.entity.PostComment;
import jeju.oneroom.postComment.mapper.PostCommentMapper;
import jeju.oneroom.postComment.repository.PostCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/posts/{post-id}/postComments")
@RequiredArgsConstructor
public class PostCommentController {

    private final PostCommentMapper mapper;
    private final PostCommentRepository postCommentRepository;

    @PostMapping
    public ResponseEntity<?> post(@PathVariable("post-id") @Positive long postId) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{postComment-id}")
    public ResponseEntity<?> patch(@PathVariable("post-id") @Positive long postId, @PathVariable("postComment-id") @Positive long postCommentId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{postComment-id}")
    public ResponseEntity<?> delete(@PathVariable("post-id") @Positive long postId, @PathVariable("postComment-id") @Positive long postCommentId) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}