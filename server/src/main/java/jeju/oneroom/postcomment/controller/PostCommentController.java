package jeju.oneroom.postcomment.controller;

import jeju.oneroom.common.dto.MultiResponseDto;
import jeju.oneroom.post.entity.Post;
import jeju.oneroom.post.service.PostService;
import jeju.oneroom.postcomment.dto.PostCommentDto;
import jeju.oneroom.postcomment.entity.PostComment;
import jeju.oneroom.postcomment.service.PostCommentService;
import jeju.oneroom.user.entity.User;
import jeju.oneroom.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@Validated
@RestController
@RequiredArgsConstructor
public class PostCommentController {

    private final UserService userService;
    private final PostService postService;
    private final PostCommentService postCommentService;

    // 특정 게시글에 댓글 추가 기능
    @PostMapping("/posts/{post-id}/comments")
    public ResponseEntity<Long> postComment(@PathVariable("post-id") @Positive long postId,
                                            @Valid @RequestBody PostCommentDto.Post postDto) {
        Post post = postService.findVerifiedPost(postId);
        User user = userService.verifyExistsUserByEmail(postDto.getUserEmail());
        PostComment createdPostComment = postCommentService.createPostComment(postDto, post, user);

        return new ResponseEntity<>(createdPostComment.getId(), HttpStatus.CREATED);
    }

    // 특정 게시글의 댓글 수정 기능
    @PatchMapping("/posts/{post-id}/comments/{comments-id}")
    public ResponseEntity<Long> patchComment(@PathVariable("post-id") @Positive long postId,
                                             @PathVariable("comments-id") @Positive long postCommentId,
                                             @Valid @RequestBody PostCommentDto.Patch patchDto) {
        postService.findVerifiedPost(postId);   // 존재하는 게시글인지부터 검증
        patchDto.setPostCommentId(postCommentId);
        User user = userService.verifyExistsUserByEmail(patchDto.getUserEmail());

        PostComment updatePostComment = postCommentService.updatePostComment(user, patchDto);

        return new ResponseEntity<>(updatePostComment.getId(), HttpStatus.OK);
    }

    // 사용자가 작성한 다중 댓글 조회 (최신순)
    @GetMapping("/comments/users/{user-id}")
    public ResponseEntity<MultiResponseDto<PostCommentDto.Response>> getPostCommentsByUser(@PathVariable("user-id") @Positive long userId,
                                                                                           @RequestParam int page,
                                                                                           @RequestParam int size) {
        User user = userService.verifyExistsUser(userId);
        Page<PostCommentDto.Response> findPostComments = postCommentService.findPostCommentsByUser(user, page, size);

        return new ResponseEntity<>(new MultiResponseDto<>(findPostComments), HttpStatus.OK);
    }

    // 특정 게시글의 댓글 삭제 기능
    @DeleteMapping("/posts/{post-id}/comments/{comments-id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable("post-id") @Positive long postId,
                                                    @PathVariable("comments-id") @Positive long postCommentId,
                                                    @Valid @RequestBody PostCommentDto.Delete deleteDto) {
        postService.findVerifiedPost(postId);   // 존재하는 게시글인지부터 검증
        User user = userService.verifyExistsUserByEmail(deleteDto.getUserEmail());

        postCommentService.deletePostComment(user, postCommentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}