package jeju.oneroom.user.controller;

import jeju.oneroom.area.entity.Area;
import jeju.oneroom.area.service.AreaService;
import jeju.oneroom.user.dto.UserDto;
import jeju.oneroom.user.entity.User;
import jeju.oneroom.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@Slf4j
@Validated
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AreaService areaService;

    @PostMapping
    public ResponseEntity<Long> post(@Valid @RequestBody UserDto.Post postDto) {
        User user = userService.createUser(postDto);
        return new ResponseEntity<>(user.getId(), HttpStatus.CREATED);
    }

    @PatchMapping("/{user-id}")
    public ResponseEntity<Long> patch(@PathVariable("user-id") @Positive Long userId,
                                                  @Valid @RequestBody UserDto.Patch patchDto) {
        patchDto.setUserId(userId);
        Area area = areaService.findVerifiedAreaByAreaCode(patchDto.getAreaCode());
        User user = userService.updateUser(patchDto, area);
        return new ResponseEntity<>(user.getId(), HttpStatus.OK);
    }

    @GetMapping("/{user-id}")
    public ResponseEntity<UserDto.Response> get(@PathVariable("user-id") @Positive Long userId) {
        UserDto.Response response = userService.findUser(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity<Void> delete(@PathVariable("user-id") @Positive Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
