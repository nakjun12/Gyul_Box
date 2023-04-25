package jeju.oneroom.Post.entitiy;

import jeju.oneroom.Post.entitiy.Post;
import jeju.oneroom.Post.repository.PostRepository;
import jeju.oneroom.postComment.entity.PostComment;
import jeju.oneroom.user.entity.User;
import jeju.oneroom.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@Rollback(false)
class PostTest {

    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;


    @Test
    public void 양도게시글_생성() throws Exception {

        //given
        User user = getUser();
        List<PostComment> postComments = new ArrayList<>();

        PostComment postComment = getPostComment();
        postComments.add(postComment);

        User savedUser = userRepository.save(user);
        Post post = getPost(savedUser, postComments);

        //then
        Post savedPost = postRepository.save(post); // db에 저장
        Post findPost = postRepository.findById(savedPost.getId()).orElse(null); // db에서 id로 찾아옴

        assertEquals(post.getContent(), findPost.getContent()); // 직접 만든 Post와 db에 저장 후 찾아온 것 비교
        assertEquals(post.getUser().getNickname(), findPost.getUser().getNickname()); // 직접 만든 Post와 db에 저장 후 찾아온 것 비교
    }

    private Post getPost(User savedUser, List<PostComment> postComments) {
        return Post.builder()
                .title("안녕하세요")
                .user(savedUser)
                .postComments(postComments)
                .content("양도 원합니다")
                .build();
    }

    private User getUser() {
        User user = User.builder()
                .nickname("망나니 개발자")
                .email("aaa@naver.com")
                .build();
        return user;
    }

    private PostComment getPostComment() {
        PostComment postComment = PostComment.builder()
                .content("제가 양도 받고 싶어요")
                .build();
        return postComment;
    }
}