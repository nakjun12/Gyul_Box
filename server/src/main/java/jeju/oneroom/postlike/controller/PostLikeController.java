package jeju.oneroom.postlike.controller;

import jeju.oneroom.post.entity.Post;
import jeju.oneroom.post.service.PostService;
import jeju.oneroom.postlike.service.PostLikeService;
import jeju.oneroom.user.entity.User;
import jeju.oneroom.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/likes")
public class PostLikeController {

    private final UserService userService;
    private final PostService postService;
    private final PostLikeService postLikeService;

    // 리뷰에 대한 좋아요 클릭
    @PutMapping("/{post-id}/{user-id}")
    public ResponseEntity<Void> putLikes(@PathVariable("post-id") @Positive long postId,
                                         @PathVariable("user-id") @Positive long userId) {
        Post post = postService.findVerifiedPost(postId);
        User user = userService.verifyExistsUser(userId);
        postLikeService.pushLike(post, user);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
