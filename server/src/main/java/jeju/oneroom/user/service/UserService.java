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

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Transactional
    public User createUser(UserDto.Post postDto) {
        return userRepository.save(userMapper.postDtoToUser(postDto));
    }

    @Transactional
    public User updateUser(UserDto.Patch patchDto, Area area) {
        User foundUser = verifyExistsUser(patchDto.getId());
        foundUser.update(patchDto.getNickname(), area);
        return foundUser;
    }

    public UserDto.Response findUser(Long userId) {
        User foundUser = verifyExistsUser(userId);
        return userMapper.userToResponseDto(foundUser);
    }

    @Transactional
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public User verifyExistsUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("USER_NOT_FOUND"));
    }
}
