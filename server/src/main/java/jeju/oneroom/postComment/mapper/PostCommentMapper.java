package jeju.oneroom.postComment.mapper;

import jeju.oneroom.postComment.dto.PostCommentDto;
import jeju.oneroom.postComment.entity.PostComment;
import jeju.oneroom.user.mapper.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {UserMapper.class})
public interface PostCommentMapper {
    PostComment postDtoToPostComment(PostCommentDto.Post postDto);

    PostComment patchDtoToPostComment(PostCommentDto.Patch patchDto);

    @Mapping(target = "writer", source = "user")
    PostCommentDto.Response postCommentToResponseDto(PostComment postComment);

    List<PostCommentDto.Response> postCommentsToResponseDtos(List<PostComment> postComments);
}
