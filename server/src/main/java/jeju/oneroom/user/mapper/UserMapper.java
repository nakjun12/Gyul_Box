package jeju.oneroom.user.mapper;

import jeju.oneroom.user.dto.UserDto;
import jeju.oneroom.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User postDtoToUser(UserDto.Post postDto);

    User patchDtoToUser(UserDto.Patch patchDto);

    UserDto.Response userToResponseDto(User user);

    List<UserDto.Response> usersToResponseDtos(List<User> users);

    UserDto.SimpleResponseDto userToSimpleResponseDto(User user);

//    default UserDto.Response userToResponseDto(User user) {
//        UserDto.Response response = new UserDto.Response();
//        response.builder()
//                .id(user.getId())
//                .email(user.getEmail())
//                .town(user.getTown().getTownName())
//                .writer(userToSimpleResponseDto(user))
//                .build();
//        return response;
//    }
}
