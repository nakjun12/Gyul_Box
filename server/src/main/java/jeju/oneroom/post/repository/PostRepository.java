package jeju.oneroom.post.repository;

import jeju.oneroom.post.entitiy.Post;
import jeju.oneroom.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByUser(User user, Pageable pageable);

    Page<Post> findByTitleContaining(String keyword, Pageable pageable); // 제목으로 게시글 찾기

    //    Page<Post> findAll(Pageable pageable);
    List<Post> findTop5ByOrderByCreatedAtDesc();
}
