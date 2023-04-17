package jeju.oneroom.Post.mapper;

import jeju.oneroom.Post.dto.PostDto;
import jeju.oneroom.Post.entitiy.Post;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {
    Post PostDtoToPost(PostDto.Post postDto);

    Post patchDtoToPost(PostDto.Patch patchDto);

    PostDto.Response postToResponseDto(Post post);

    List<PostDto.Response> postsToResponseDtos(List<Post> posts);
}
