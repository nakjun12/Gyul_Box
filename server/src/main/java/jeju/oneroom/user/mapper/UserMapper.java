package jeju.oneroom.user.mapper;

import jeju.oneroom.area.entity.Area;
import jeju.oneroom.user.dto.UserDto;
import jeju.oneroom.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User postDtoToUser(UserDto.Post postDto);

    User patchDtoToUser(UserDto.Patch patchDto);

    //@Mapping(target = "town", expression = "user.get")
    UserDto.Response userToResponseDto(User user);

    List<UserDto.Response> usersToResponseDtos(List<User> users);

    UserDto.SimpleResponseDto userToSimpleResponseDto(User user);

    default String toString(Area area){
        return area.getAreaName();
    }
}
