package jeju.oneroom.postComment.entity;

import jeju.oneroom.Post.entitiy.Post;
import jeju.oneroom.Post.repository.PostRepository;
import jeju.oneroom.postComment.repository.PostCommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class PostCommentTest {

    @Autowired
    PostCommentRepository postCommentRepository;
    @Autowired
    PostRepository postRepository;

    @Test
    public void 양도게시글댓글_생성() throws Exception {

        Post post = postRepository.findById(2L).orElse(null);
        //given
        PostComment postComment = getPostComment();
        postComment.builder()
                .post(post)
                .build();

        //then
        PostComment savedPostComment = postCommentRepository.save(postComment);  // db에 저장
        PostComment findPostComment = postCommentRepository.findById(savedPostComment.getId()).orElse(null); // db에서 id로 찾아옴

        assertEquals(postComment.getContent(), findPostComment.getContent()); // 직접 만든 PostComment와 db에 저장 후 찾아온 것 비교
    }

    private PostComment getPostComment() {
        PostRepository postRepository;
        PostComment postComment = PostComment.builder()
                .content("제가 양도 받고 싶어요")
                .build();
        return postComment;
    }

}