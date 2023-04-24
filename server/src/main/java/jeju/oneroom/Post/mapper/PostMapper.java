package jeju.oneroom.Post.mapper;

import jeju.oneroom.Post.dto.PostDto;
import jeju.oneroom.Post.entitiy.Post;
import jeju.oneroom.postComment.mapper.PostCommentMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {PostCommentMapper.class})
public interface PostMapper {
    Post PostDtoToPost(PostDto.Post postDto);

    Post patchDtoToPost(PostDto.Patch patchDto);

    @Mapping(target = "writer", source = "user")
    PostDto.Response postToResponseDto(Post post);

    @Mapping(target = "nickname", expression = "java(post.getUser().getNickname())")
    PostDto.SimpleResponseDto postToSimpleResponseDto(Post post);

    List<PostDto.Response> postsToResponseDtos(List<Post> posts);
}
