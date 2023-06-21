package jeju.oneroom.post.repository;

import jeju.oneroom.post.entity.Post;
import jeju.oneroom.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByUserOrderByCreatedAtDesc(User user, Pageable pageable);

    Page<Post> findAllByOrderByCreatedAtDesc(Pageable pageable);

    Page<Post> findAllByHouseInfoAddressIsIgnoreCaseOrderByCreatedAtDesc(String address, Pageable pageable);

    Page<Post> findAllByTitleContainsIgnoreCaseOrderByCreatedAtDesc(String title, Pageable pageable);
}
