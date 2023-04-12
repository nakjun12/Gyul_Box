package jeju.oneroom.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    @PostMapping
    public ResponseEntity<?> post(){
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/reviews/{review-id}")
    public ResponseEntity<?> patch(@PathVariable("review-id") long reviewId){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/reviews/{review-id}")
    public ResponseEntity<?> delete(@PathVariable("review-id") long reviewId){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //  리뷰 단일 조회
    @GetMapping("/reviews/{review-id}")
    public ResponseEntity<?> findReview(@PathVariable("review-id") long reviewId){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 최신 리뷰 5개
    @GetMapping("/reviews/latest5")
    public ResponseEntity<?> findTop5LatestReviews(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 추천 순 리뷰 5개
    @GetMapping("/reviews/hottest5")
    public ResponseEntity<?> findTop5HottestReviews(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 유저 관심 지역 리뷰 5개
    @GetMapping("users/{user-id}/user-towns/reviews")
    public ResponseEntity<?> findUserTownReviews(@PathVariable("user-id") long userId){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 유저 리뷰 찾기  추후 findUserTownReviews메서드와 통합 시도.
    @GetMapping("users/{user-id}/reviews")
    public ResponseEntity<?> findUserReviews(@PathVariable("user-id") long userId){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Town에 따른 거주 리뷰 아마 30개?
    @GetMapping("towns/{town-id}/reviews")
    public ResponseEntity<?> findTownReviews(@PathVariable("town-id") long townCode){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
