package jeju.oneroom.postcomment.repository;

import jeju.oneroom.postcomment.entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentRepository extends JpaRepository<PostComment, Long>, PostCommentCustomRepository {
}
