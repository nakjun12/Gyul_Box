package jeju.oneroom.post.mapper;

import jeju.oneroom.post.dto.PostDto;
import jeju.oneroom.post.entity.Post;
import jeju.oneroom.postcomment.mapper.PostCommentMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {PostCommentMapper.class})
public interface PostMapper {
    Post postDtoToPost(PostDto.Post postDto);

    @Mapping(target = "writer", source = "user")
    @Mapping(target = "houseAddress", expression = "java(post.getHouseInfo().getPlatPlc())")
    PostDto.Response postToResponseDto(Post post);

    @Mapping(target = "nickname", expression = "java(post.getUser().getNickname())")
    PostDto.SimpleResponseDto postToSimpleResponseDto(Post post);

    List<PostDto.Response> postsToResponseDtos(List<Post> posts);
}
