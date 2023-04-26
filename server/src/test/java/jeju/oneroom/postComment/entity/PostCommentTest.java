package jeju.oneroom.postComment.entity;

import jeju.oneroom.Post.entitiy.Post;
import jeju.oneroom.Post.repository.PostRepository;
import jeju.oneroom.area.entity.Area;
import jeju.oneroom.common.entity.Coordinate;
import jeju.oneroom.postComment.entity.PostComment;
import jeju.oneroom.postComment.repository.PostCommentRepository;
import jeju.oneroom.user.entity.User;
import jeju.oneroom.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@Rollback(false)
class PostCommentTest {

    @Autowired
    PostCommentRepository postCommentRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;


    @Test
    public void 양도게시글댓글_생성() throws Exception {

        //given
        User user = getUser(getTown(getCoordinate()));
        User savedUser = userRepository.save(user);
        Post post = getPost(savedUser);
        Post savedPost = postRepository.save(post);
        PostComment postComment = getPostComment(savedPost, savedUser);

        //then
        PostComment savedPostComment = postCommentRepository.save(postComment);  // db에 저장
        PostComment findPostComment = postCommentRepository.findById(savedPostComment.getId()).orElse(null); // db에서 id로 찾아옴

        assertEquals(postComment.getContent(), findPostComment.getContent()); // 직접 만든 PostComment와 db에 저장 후 찾아온 것 비교
    }

    private PostComment getPostComment(Post post, User user) {
        PostComment postComment = PostComment.builder()
                .content("제가 양도 받고 싶어요")
                .post(post)
                .user(user)
                .build();
        return postComment;
    }

    private Post getPost(User savedUser) {
        return Post.builder()
                .title("안녕하세요")
                .user(savedUser)
                .content("양도 원합니다")
                .build();
    }

    private User getUser(Area area) {
        User user = User.builder()
                .nickname("망나das니 개발자")
                .email("adsdaa@naver.com")
                .area(area)
                .build();
        return user;
    }

    private Coordinate getCoordinate() {
        Coordinate coordinate = new Coordinate(11.11111, 11.11111);
        return coordinate;
    }

    private Area getTown(Coordinate coordinate) {
        Area area = Area.builder()
                .areaCode(11111L)
                .areaName("동춘동")
                .coordinate(coordinate)
                .build();
        return area;
    }
}