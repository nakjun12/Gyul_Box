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


    // User 정보 수정
    @PatchMapping("/{user-id}")
    public ResponseEntity<Long> patch(@PathVariable("user-id") @Positive Long userId,
                                      @Valid @RequestBody UserDto.Patch patchDto) {
        patchDto.setUserId(userId);
        Area area = areaService.findVerifiedAreaByAreaCode(patchDto.getAreaCode());
        User user = userService.updateUser(patchDto, area);
        return new ResponseEntity<>(user.getId(), HttpStatus.OK);
    }

    // 마이페이지에서 사용하는 user 정보 조회
    @GetMapping("/{user-id}")
    public ResponseEntity<UserDto.Response> get(@PathVariable("user-id") @Positive Long userId) {
        UserDto.Response response = userService.findUser(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // id에 해당하는 유저 삭제
    @DeleteMapping("/{user-id}")
    public ResponseEntity<Void> delete(@PathVariable("user-id") @Positive Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
