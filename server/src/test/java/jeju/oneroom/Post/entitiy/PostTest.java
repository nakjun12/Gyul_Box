package jeju.oneroom.Post.entitiy;

import jeju.oneroom.Post.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostTest {

    @Autowired
    PostRepository postRepository;

    @Test
    public void 양도게시글_생성() throws Exception {

        //given
        Post post = getPost();

        //then
        Post savedPost = postRepository.save(post); // db에 저장
        Post findPost = postRepository.findById(savedPost.getId()).orElse(null); // db에서 id로 찾아옴

        assertEquals(post.getContent(), findPost.getContent()); // 직접 만든 Post와 db에 저장 후 찾아온 것 비교
    }

    private Post getPost() {
        return Post.builder()
                .title("안녕하세요")
                .content("양도 원합니다")
                .build();
    }
}