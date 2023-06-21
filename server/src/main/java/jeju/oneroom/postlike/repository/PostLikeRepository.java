package jeju.oneroom.postlike.repository;

import jeju.oneroom.post.entity.Post;
import jeju.oneroom.postlike.entity.PostLike;
import jeju.oneroom.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    // 해당 게시글에 해당 유저의 좋아요 가져오기
    Optional<PostLike> findByPostAndUser(Post post, User user);
}
