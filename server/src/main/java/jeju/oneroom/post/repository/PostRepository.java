package jeju.oneroom.post.repository;

import jeju.oneroom.houseInfo.entity.HouseInfo;
import jeju.oneroom.post.entity.Post;
import jeju.oneroom.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    @EntityGraph(attributePaths = {"user", "houseInfo", "postComments","postComments.user"})
    Optional<Post> findById(long postId);

    Page<Post> findAllByUserOrderByCreatedAtDesc(User user, Pageable pageable);

    @EntityGraph(attributePaths = {"user"})
    Page<Post> findAllByOrderByCreatedAtDesc(Pageable pageable);

    @EntityGraph(attributePaths = {"user"})
    Page<Post> findAllByTitleContainsIgnoreCaseOrderByCreatedAtDesc(String title, Pageable pageable);

    @EntityGraph(attributePaths = {"user"})
    Page<Post> findByHouseInfoIn(List<HouseInfo> houseInfos, Pageable pageable);
}
