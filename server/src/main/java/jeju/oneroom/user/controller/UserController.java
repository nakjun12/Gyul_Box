package jeju.oneroom.user.controller;

import jeju.oneroom.user.dto.UserDto;
import jeju.oneroom.user.entity.User;
import jeju.oneroom.user.mapper.UserMapper;
import jeju.oneroom.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserMapper mapper;
    private final UserRepository repository;

    @PostMapping
    public ResponseEntity<?> post(UserDto.Post postDto) {
        return new ResponseEntity<>(mapper.postDtoToUser(postDto), HttpStatus.CREATED);
    }

    @PatchMapping("/{user-id}")
    public ResponseEntity<?> patch(@PathVariable("user-id") @Positive long Id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{user-id}")
    public ResponseEntity<?> get(@PathVariable("user-id") @Positive long Id) {
        User findUser = repository.findById(Id).orElse(null);
        return new ResponseEntity<>(mapper.userToResponseDto(findUser), HttpStatus.OK);
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity<?> delete() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}