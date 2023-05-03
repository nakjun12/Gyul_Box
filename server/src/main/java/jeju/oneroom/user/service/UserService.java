package jeju.oneroom.user.service;

import jeju.oneroom.area.entity.Area;
import jeju.oneroom.area.repository.AreaRepository;
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
    private final AreaRepository areaRepository;

    //회원 생성
    public UserDto.Response createUser(UserDto.Post postDto) {
        User createUser = userRepository.save(userMapper.postDtoToUser(postDto));
        return userMapper.userToResponseDto(createUser);
    }

    //회원 정보 수정
    public UserDto.Response updateUser(UserDto.Patch patchDto) {
        User findUser = userMapper.patchDtoToUser(patchDto);
        Area area = areaRepository.findById(patchDto.getAreaCode()).orElse(null);
        findUser.setArea(area);
        return userMapper.userToResponseDto(findUser);
    }

    //회원 단건 조회
    public UserDto.Response getUser(long userId) {
        return userMapper.userToResponseDto(userRepository.findById(userId).orElse(null));
    }

    //회원 탈퇴
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }

    // 유효한 회원 확인
    public User findVerifiedUser(long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("USER_NOT_FOUND"));
    }
}
