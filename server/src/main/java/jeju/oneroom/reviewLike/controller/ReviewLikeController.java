package jeju.oneroom.reviewLike.controller;

import jeju.oneroom.review.entity.Review;
import jeju.oneroom.review.service.ReviewService;
import jeju.oneroom.reviewLike.service.ReviewLikeService;
import jeju.oneroom.user.entity.User;
import jeju.oneroom.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
public class ReviewLikeController {

    private final ReviewLikeService reviewLikeService;
    private final ReviewService reviewService;
    private final UserService userService;

    // 리뷰에 대한 좋아요 클릭
    @PutMapping("/reviews/likes/{review-id}/{user-id}")
    public ResponseEntity<?> putLikes(@PathVariable("review-id") @Positive long reviewId,
                                      @PathVariable("user-id") @Positive long userId) {
        User user = userService.verifyExistsUser(userId);
        Review review = reviewService.findVerifiedReview(reviewId);
        reviewLikeService.pushLike(review, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
