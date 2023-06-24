package jeju.oneroom.postcomment.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jeju.oneroom.postcomment.entity.PostComment;
import jeju.oneroom.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jeju.oneroom.post.entity.QPost.post;
import static jeju.oneroom.postcomment.entity.QPostComment.postComment;

@Repository
@RequiredArgsConstructor
public class PostCommentCustomRepositoryImpl implements PostCommentCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<PostComment> findPostCommentsByUser(User user, Pageable pageable) {
        List<PostComment> postComments = getPostCommentsByUser(user, pageable);

        Long count = jpaQueryFactory.select(postComment.count())
                .from(postComment)
                .where(postComment.user.eq(user))
                .fetchOne();

        return new PageImpl<>(postComments, pageable, count);
    }

    private List<PostComment> getPostCommentsByUser(User user, Pageable pageable) {
        return jpaQueryFactory.selectFrom(postComment)
                .leftJoin(postComment.post, post).fetchJoin()
                .where(postComment.user.eq(user))
                .orderBy(postComment.modifiedAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
