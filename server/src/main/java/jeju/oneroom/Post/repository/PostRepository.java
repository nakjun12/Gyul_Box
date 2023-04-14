package jeju.oneroom.Post.repository;

import jeju.oneroom.Post.entitiy.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
