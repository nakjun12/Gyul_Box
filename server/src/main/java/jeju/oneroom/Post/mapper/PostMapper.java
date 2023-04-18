package jeju.oneroom.Post.mapper;

import jeju.oneroom.Post.dto.PostDto;
import jeju.oneroom.Post.entitiy.Post;
import jeju.oneroom.user.dto.UserDto;
import jeju.oneroom.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {
    Post PostDtoToPost(PostDto.Post postDto);

    Post patchDtoToPost(PostDto.Patch patchDto);

    PostDto.Response postToResponseDto(Post post);
    PostDto.SimpleResponseDto postToSimpleResponseDto(Post post);

    List<PostDto.Response> postsToResponseDtos(List<Post> posts);

//    default PostDto.SimpleResponseDto postToSimpleResponseDto(Post post) {
//        PostDto.SimpleResponseDto response = new PostDto.SimpleResponseDto();
//        response.builder()
//                .id(post.getId())
//                .nickname(post.getUser().getNickname())
//                .title(post.getTitle())
//                .content(post.getContent())
//                .likes(post.getLikes())
//                .build();
//        return response;
//    }
}
