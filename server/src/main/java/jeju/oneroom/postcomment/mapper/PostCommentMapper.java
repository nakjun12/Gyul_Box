package jeju.oneroom.postcomment.mapper;

import jeju.oneroom.postcomment.dto.PostCommentDto;
import jeju.oneroom.postcomment.entity.PostComment;
import jeju.oneroom.user.mapper.UserMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {UserMapper.class})
public interface PostCommentMapper {
    PostComment postDtoToPostComment(PostCommentDto.Post postDto);

    @Mapping(target = "writer", source = "user")
    PostCommentDto.Response postCommentToResponseDto(PostComment postComment);

    List<PostCommentDto.Response> postCommentsToResponseDtos(List<PostComment> postComments);
}
