package jeju.oneroom.postcomment.repository;

import jeju.oneroom.postcomment.entity.PostComment;
import jeju.oneroom.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
    @EntityGraph(attributePaths = "post")
    Page<PostComment> findAllByUserOrderByCreatedAtDesc(User user, Pageable pageable);
}
