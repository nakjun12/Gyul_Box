package jeju.oneroom.Post.repository;

import jeju.oneroom.Post.entitiy.Post;
import jeju.oneroom.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByUser(User user);
    List<Post> findByTitleContaining(String keyword); // 제목으로 게시글 찾기
}
