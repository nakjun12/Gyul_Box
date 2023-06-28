package jeju.oneroom.post.service;

import jeju.oneroom.exception.BusinessLogicException;
import jeju.oneroom.exception.ExceptionCode;
import jeju.oneroom.houseInfo.entity.HouseInfo;
import jeju.oneroom.post.dto.PostDto;
import jeju.oneroom.post.entity.Post;
import jeju.oneroom.post.mapper.PostMapper;
import jeju.oneroom.post.repository.PostRepository;
import jeju.oneroom.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostMapper postMapper;
    private final PostRepository postRepository;

    // 게시글 생성
    @Transactional
    public Post createPost(PostDto.Post postDto, HouseInfo houseInfo, User user) {
        Post post = postMapper.postDtoToPost(postDto);
        post.setProperties(houseInfo, user);

        return postRepository.save(post);
    }

    // 게시글 수정
    @Transactional
    public Post updatePost(User user, PostDto.Patch patchDto) {
        Post verifiedPost = findVerifiedPost(patchDto.getPostId());

        if (verifiedPost.isAuthor(user)) {
            verifiedPost.update(patchDto.getTitle(), patchDto.getContent());
        } else {
            throw new BusinessLogicException(ExceptionCode.NO_PERMISSION_TO_EDIT);
        }

        return verifiedPost;
    }

    // 게시글 id로 단일 게시글 조회
    @Transactional
    public PostDto.Response findPostByPostId(long postId) {
        Post verifiedPost = findVerifiedPost(postId);
        postRepository.updateViewCount(postId);  // 단일 게시글 조회 시, 조회 수 1씩 증가

        return postMapper.postToResponseDto(verifiedPost);
    }

    // 유저의 모든 게시글 최신순으로 조회
    public Page<PostDto.SimpleResponseDto> findPostsByUserId(User user, int page, int size) {
        return postRepository.findAllByUserOrderByCreatedAtDesc(user, PageRequest.of(page - 1, size))
                .map(postMapper::postToSimpleResponseDto);
    }

    // 제목을 통한 다중 게시글 최신순으로 조회
    public Page<PostDto.SimpleResponseDto> findPostsByTitle(String title, int page, int size) {
        return postRepository.findPostsByTitleContains(title, PageRequest.of(page - 1, size))
                .map(postMapper::postToSimpleResponseDto);
    }

    // 모든 게시글 최신순으로 조회
    public Page<PostDto.SimpleResponseDto> findAllPost(int page, int size) {
        return postRepository.findAllPosts(PageRequest.of(page - 1, size))
                .map(postMapper::postToSimpleResponseDto);
    }

    // 다수의 건물에 속한 다중 게시글 조회
    public Page<PostDto.SimpleResponseDto> findPostsByHouseInfos(List<HouseInfo> houseInfos, int page, int size) {
        return postRepository.findPostsByHouseInfoIn(houseInfos, PageRequest.of(page - 1, size))
                .map(postMapper::postToSimpleResponseDto);
    }

    // 게시글 id로 단일 게시글 삭제
    @Transactional
    public void deletePost(User user, long postId) {
        Post verifiedPost = findVerifiedPost(postId);

        if (verifiedPost.isAuthor(user)) {
            postRepository.deleteById(postId);
        } else {
            throw new BusinessLogicException(ExceptionCode.NO_PERMISSION_TO_DELETE);
        }
    }

    // 게시글 id로 존재 여부 검증
    @Transactional
    public Post findVerifiedPost(long postId) {
        return postRepository.findPostById(postId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_POST));
    }
}
