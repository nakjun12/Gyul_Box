package jeju.oneroom.reviewLike.controller;

import jeju.oneroom.reviewLike.service.ReviewLikeService;
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

    @PutMapping("/reviews/likes/{review-id}/{user-id}")
    public ResponseEntity<?> putLikes(@PathVariable("review-id") @Positive long reviewId,
                                      @PathVariable("user-id") @Positive long userId) {
        reviewLikeService.pushLike(reviewId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
