package jeju.oneroom.post.entity;

import jeju.oneroom.houseInfo.entity.HouseInfo;
import jeju.oneroom.houseInfo.repository.HouseInfoRepository;
import jeju.oneroom.post.repository.PostRepository;
import jeju.oneroom.postcomment.entity.PostComment;
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
    @Autowired
    HouseInfoRepository houseInfoRepository;


    @Test
    public void 양도게시글_생성() throws Exception {

        //given
        for (int i = 1; i < 3; i++) {
            HouseInfo houseInfo = houseInfoRepository.findById((long) i).orElse(null);
            for (int j = 1; j < 10000; j++) {
                User user = userRepository.findById((long) j).orElse(null);
                Post post = getPost(user, houseInfo);
                postRepository.save(post);
            }
        }

        //then
//        Post savedPost = postRepository.save(post); // db에 저장
//        Post findPost = postRepository.findById(savedPost.getId()).orElse(null); // db에서 id로 찾아옴
//
//        assertEquals(post.getContent(), findPost.getContent()); // 직접 만든 Post와 db에 저장 후 찾아온 것 비교
//        assertEquals(post.getUser().getNickname(), findPost.getUser().getNickname()); // 직접 만든 Post와 db에 저장 후 찾아온 것 비교
    }

    private Post getPost(User savedUser, HouseInfo houseInfo) {
        return Post.builder()
                .title("안녕하세요")
                .user(savedUser)
                .content("양도 원합니다")
                .views(10)
                .houseInfo(houseInfo)
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