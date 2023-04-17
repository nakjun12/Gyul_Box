package jeju.oneroom.Post.controller;

import jeju.oneroom.Post.entitiy.Post;
import jeju.oneroom.Post.mapper.PostMapper;
import jeju.oneroom.Post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostMapper mapper;
    private final PostRepository repository;

    @PostMapping("/posts") // 게시글을 작성할 수 있다.
    public ResponseEntity<?> post() {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/posts/likes/{post-id}") // 게시글 좋아요 기능
    public ResponseEntity<?> putLikes(@PathVariable("post-id") @Positive long postId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/posts/{post-id}") // 게시글을 수정할 수 있다.
    public ResponseEntity<?> patch(@PathVariable("post-id") @Positive long postId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/posts/{post-id}") // 게시글을 가져올 수 있다.
    public ResponseEntity<?> findPost(@PathVariable("post-id") @Positive long postId) {
        Post findPost = repository.findById(postId).orElse(null);
        return new ResponseEntity<>(mapper.postToResponseDto(findPost), HttpStatus.OK);
    }

    @GetMapping("/user/{user-id}/posts") // user-id로 유저 검색 후 유저가 작성한 글 가져오기
    public ResponseEntity<?> findUserPost(@PathVariable("user-id") @Positive long userId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/posts") // 게시글을 페이지네이션으로 가져오고 최신순 추천순으로 정렬을 할 수 있다.
    public ResponseEntity<?> findPosts(@PathVariable("post-id") @Positive long postId) {
        /**
         * Post 최신순 추천순 정렬 기능
         */
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/posts/search")  // 게시글을 제목으로 검색할 수 있다.
    public ResponseEntity<?> findPost(@RequestParam String query) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/posts/latest5") // 최신순 5개
    public ResponseEntity<?> findTop5LatestReviews() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/posts/hottest5") // 추천순 5개
    public ResponseEntity<?> findTop5HottestReviews() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/posts/{post-id}")   // 게시글을 삭제할 수 있다.
    public ResponseEntity<?> delete(@PathVariable("post-id") @Positive long postId) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
