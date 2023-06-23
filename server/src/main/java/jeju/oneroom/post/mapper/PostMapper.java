package jeju.oneroom.post.mapper;

import jeju.oneroom.houseInfo.entity.HouseInfo;
import jeju.oneroom.houseInfo.mapper.HouseInfoMapper;
import jeju.oneroom.post.dto.PostDto;
import jeju.oneroom.post.entity.Post;
import jeju.oneroom.postcomment.mapper.PostCommentMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {PostCommentMapper.class, HouseInfoMapper.class})
public interface PostMapper {
    Post postDtoToPost(PostDto.Post postDto);

    @Mapping(target = "writer", source = "user")
    PostDto.Response postToResponseDto(Post post);

    @Mapping(target = "nickname", expression = "java(post.getUser().getNickname())")
    PostDto.SimpleResponseDto postToSimpleResponseDto(Post post);
}
