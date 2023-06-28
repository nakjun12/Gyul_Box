package jeju.oneroom.postcomment.service;

import jeju.oneroom.exception.BusinessLogicException;
import jeju.oneroom.exception.ExceptionCode;
import jeju.oneroom.post.entity.Post;
import jeju.oneroom.postcomment.dto.PostCommentDto;
import jeju.oneroom.postcomment.entity.PostComment;
import jeju.oneroom.postcomment.mapper.PostCommentMapper;
import jeju.oneroom.postcomment.repository.PostCommentRepository;
import jeju.oneroom.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PostCommentService {

    private final PostCommentMapper postCommentMapper;
    private final PostCommentRepository postCommentRepository;

    // 게시판 댓글 생성
    public PostComment createPostComment(PostCommentDto.Post postDto, Post post, User user) {
        PostComment postComment = postCommentMapper.postDtoToPostComment(postDto);
        postComment.setProperties(post, user);

        return postCommentRepository.save(postComment);
    }

    // 게시판 댓글 수정
    public PostComment updatePostComment(User user, PostCommentDto.Patch patchDto) {
        PostComment verifiedPostComment = findVerifiedPostComment(patchDto.getPostCommentId());

        if (verifiedPostComment.isAuthor(user)) {
            verifiedPostComment.update(patchDto.getContent());
        } else {
            throw new BusinessLogicException(ExceptionCode.NO_PERMISSION_TO_EDIT);
        }

        return verifiedPostComment;
    }

    // 유저의 모든 게시글 댓글 최신순으로 조회
    @Transactional(readOnly = true)
    public Page<PostCommentDto.Response> findPostCommentsByUser(User user, int page, int size) {
        return postCommentRepository.findPostCommentsByUser(user, PageRequest.of(page - 1, size))
                .map(postCommentMapper::postCommentToResponseDto);
    }

    // 게시판 댓글 삭제
    public void deletePostComment(User user, long postCommentId) {
        PostComment verifiedPostComment = findVerifiedPostComment(postCommentId);

        if (verifiedPostComment.isAuthor(user)) {
            postCommentRepository.deleteById(postCommentId);
        } else {
            throw new BusinessLogicException(ExceptionCode.NO_PERMISSION_TO_DELETE);
        }
    }

    private PostComment findVerifiedPostComment(long postCommentId) {
        return postCommentRepository.findById(postCommentId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_POSTCOMMENT));
    }
}
