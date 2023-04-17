package jeju.oneroom.postComment.mapper;

import jeju.oneroom.postComment.dto.PostCommentDto;
import jeju.oneroom.postComment.entity.PostComment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostCommentMapper {
    PostComment postDtoToPostComment(PostCommentDto.Post postDto);

    PostComment patchDtoToPostComment(PostCommentDto.Patch patchDto);

    PostCommentDto.Response postCommentToResponseDto(PostComment postComment);

    List<PostCommentDto.Response> postCommentsToResponseDtos(List<PostComment> postComments);
}
