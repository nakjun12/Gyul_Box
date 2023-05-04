package jeju.oneroom.user.mapper;

import jeju.oneroom.user.dto.UserDto;
import jeju.oneroom.user.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User postDtoToUser(UserDto.Post postDto);

    @Mapping(target = "areaName", source = "area.areaName")
    @Mapping(target = "profileImageUrl", ignore = true)
    UserDto.Response userToResponseDto(User user);

    UserDto.SimpleResponse userToSimpleResponseDto(User user);
}
