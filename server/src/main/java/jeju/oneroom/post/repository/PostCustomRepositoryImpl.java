package jeju.oneroom.post.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jeju.oneroom.houseInfo.entity.HouseInfo;
import jeju.oneroom.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static jeju.oneroom.houseInfo.entity.QHouseInfo.houseInfo;
import static jeju.oneroom.post.entity.QPost.post;
import static jeju.oneroom.postcomment.entity.QPostComment.postComment;
import static jeju.oneroom.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class PostCustomRepositoryImpl implements PostCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Post> findPostById(long postId) {
        Post post1 = jpaQueryFactory.selectFrom(post)
                .leftJoin(post.user, user).fetchJoin()
                .leftJoin(post.houseInfo, houseInfo).fetchJoin()
                .leftJoin(post.postComments, postComment).fetchJoin()
                .leftJoin(postComment.user, user).fetchJoin()
                .where(post.id.eq(postId))
                .fetchOne();

        return Optional.ofNullable(post1);
    }

//    @Override
//    public Page<Post> findByHouseInfoIn(List<HouseInfo> houseInfos, Pageable pageable) {
//        List<Post> posts = jpaQueryFactory.selectDistinct(post)
//                .from(post)
//                .leftJoin(post.user, user).fetchJoin()
//                .where(post.houseInfo.in(houseInfos))
//                .orderBy(post.createdAt.desc())
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//
//        return new PageImpl<>(posts, pageable, posts.size());
//    }

    @Override
    public Page<Post> findPostsByHouseInfoIn(List<HouseInfo> houseInfos, Pageable pageable) {
        List<Post> posts = getPostsByHouseInfoIn(houseInfos, pageable);

        Long count = jpaQueryFactory.select(post.count())
                .from(post)
                .where(post.houseInfo.in(houseInfos))
                .fetchOne();

        return new PageImpl<>(posts, pageable, count);
    }

    private List<Post> getPostsByHouseInfoIn(List<HouseInfo> houseInfos, Pageable pageable) {
        return jpaQueryFactory.selectFrom(post)
                .leftJoin(post.user, user).fetchJoin()
                .where(post.houseInfo.in(houseInfos))
                .orderBy(post.modifiedAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public Page<Post> findPostsByTitleContains(String title, Pageable pageable) {
        List<Post> posts = getPostsByTitleContains(title, pageable);

        Long count = jpaQueryFactory.select(post.count())
                .from(post)
                .where(post.title.containsIgnoreCase(title.toLowerCase()))
                .fetchOne();

        return new PageImpl<>(posts, pageable, count);
    }

    private List<Post> getPostsByTitleContains(String title, Pageable pageable) {
        return jpaQueryFactory.selectFrom(post)
                .leftJoin(post.user, user).fetchJoin()
                .where(post.title.toUpperCase().contains(title.toUpperCase()))
                .orderBy(post.modifiedAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public Page<Post> findAllPosts(Pageable pageable) {
        List<Post> posts = getAllPosts(pageable);

        Long count = jpaQueryFactory.select(post.count())
                .from(post)
                .fetchOne();

        return new PageImpl<>(posts, pageable, count);
    }

    private List<Post> getAllPosts(Pageable pageable) {
        return jpaQueryFactory.selectFrom(post)
                .leftJoin(post.user, user).fetchJoin()
                .orderBy(post.modifiedAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public long updateViewCount(Long postId) {
        return jpaQueryFactory.update(post)
                .set(post.views, post.views.add(1))
                .where(post.id.eq(postId))
                .execute();
    }
}
