package jeju.oneroom.user.controller;

import jeju.oneroom.user.dto.UserDto;
import jeju.oneroom.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    //회원 생성
    @PostMapping
    public ResponseEntity<?> post(@Valid @RequestBody UserDto.Post postDto) {
        UserDto.Response createUser = userService.createUser(postDto);
        return new ResponseEntity<>(createUser, HttpStatus.CREATED);
    }

    @PatchMapping("/{user-id}")
    public ResponseEntity<?> patch(@PathVariable("user-id") @Positive long userId,
                                   @Valid @RequestBody UserDto.Patch patchDto) {
        UserDto.Response user = userService.updateUser(userId, patchDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/{user-id}")
    public ResponseEntity<?> get(@PathVariable("user-id") @Positive long userId) {
        UserDto.Response findMember = userService.getMember(userId);
        return new ResponseEntity<>(findMember, HttpStatus.OK);
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity<?> delete(@PathVariable("user-id") @Positive long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}