package jeju.oneroom.user.service;

import jeju.oneroom.user.dto.UserDto;
import jeju.oneroom.user.entity.User;
import jeju.oneroom.user.mapper.UserMapper;
import jeju.oneroom.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    //회원 생성
    public UserDto.Response createUser(UserDto.Post postDto) {
        User createUser = userRepository.save(userMapper.postDtoToUser(postDto));
        return userMapper.userToResponseDto(createUser);
    }

    //회원 정보 수정
    public UserDto.Response updateUser(long userId, UserDto.Patch patchDto) {
        User findUser = userMapper.patchDtoToUser(patchDto);
        return userMapper.userToResponseDto(findUser);
    }

    //회원 단건 조회
    public UserDto.Response getMember(long userId) {
        return userMapper.userToResponseDto(userRepository.findById(userId).orElse(null));
    }

    //회원 탈퇴
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }

}
