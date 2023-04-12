package jeju.oneroom.Post.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@RestController
public class PostController {

    // 게시글을 작성할 수 있다.
    @PostMapping("/posts")
    public ResponseEntity post(){
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 게시글 좋아요 기능
    @PutMapping("/posts/likes/{post-id}")
    public ResponseEntity putLikes(@PathVariable("post-id") @Positive long postId){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 게시글을 수정할 수 있다.
    @PatchMapping("/posts/{post-id}")
    public ResponseEntity patch(@PathVariable("post-id") @Positive long postId){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 게시글을 가져올 수 있다.
    @GetMapping("/posts/{post-id}")
    public ResponseEntity findPost(@PathVariable("post-id") @Positive long postId){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // user-id로 유저 검색 후 유저가 작성한 글 가져오기
    @GetMapping("/user/{user-id}/posts")
    public ResponseEntity findUserPost(@PathVariable("user-id") @Positive long userId){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 게시글을 페이지네이션으로 가져오고 최신순 추천순으로 정렬을 할 수 있다.
    @GetMapping("/posts")
    public ResponseEntity findPosts(@PathVariable("post-id") @Positive long postId){
        /**
         * Post 최신순 추천순 정렬 기능
         */
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 게시글을 제목으로 검색할 수 있다.
    @GetMapping("/posts/search")
    public ResponseEntity findPost(@RequestParam String query){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 게시글을 삭제할 수 있다.
    @DeleteMapping("/posts/{post-id}")
    public ResponseEntity delete(@PathVariable("post-id") @Positive long postId){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
