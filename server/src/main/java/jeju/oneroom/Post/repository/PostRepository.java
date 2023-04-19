package jeju.oneroom.Post.repository;

import jeju.oneroom.Post.entitiy.Post;
import jeju.oneroom.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByUser(User user);
    Page<Post> findByTitleContaining(String keyword, Pageable pageable); // 제목으로 게시글 찾기
//    Page<Post> findAll(Pageable pageable);
    List<Post> findTop5ByOrderByCreatedAtDesc();
}
