package jeju.oneroom.post.repository;

import jeju.oneroom.houseInfo.entity.HouseInfo;
import jeju.oneroom.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PostCustomRepository {

    // 게시글 id로 단일 게시글 조회
    Optional<Post> findPostById(long postId);

    // 다수의 건물에 속한 모든 게시글 조회
    Page<Post> findPostsByHouseInfoIn(List<HouseInfo> houseInfos, Pageable pageable);

    // 제목이 포함된 다중 게시글 조회
    Page<Post> findPostsByTitleContains(String title, Pageable pageable);

    // 모든 게시글 조회
    Page<Post> findAllPosts(Pageable pageable);

    // 특정 게시글 id로 단일 게시글 조회 시, 조회수 증가
    long updateViewCount(Long postId);
}
