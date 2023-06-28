package jeju.oneroom.messageroom.controller;

import jeju.oneroom.common.dto.MultiResponseDto;
import jeju.oneroom.messageroom.dto.MessageRoomDto;
import jeju.oneroom.messageroom.service.MessageRoomService;
import jeju.oneroom.user.entity.User;
import jeju.oneroom.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/messagerooms")
public class MessageRoomController {

    private final UserService userService;
    private final MessageRoomService messageRoomService;

    // 채팅방 생성
    @PostMapping
    public ResponseEntity<MessageRoomDto.SimpleResponse> postMessageRoom(@Valid @RequestBody MessageRoomDto.Post postDto) {
        User sender = userService.verifyExistsUser(postDto.getSenderId());  // email
        User receiver = userService.verifyExistsUser(postDto.getReceiverId());

        MessageRoomDto.SimpleResponse response = messageRoomService.createMessageRoom(postDto, sender, receiver);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 특정 유저의 모든 채팅방 조회
    @GetMapping("/{user-id}")
    public ResponseEntity<MultiResponseDto<MessageRoomDto.SimpleResponse>> getMessageRooms(@PathVariable("user-id") @Positive Long userId,
                                                                                           @RequestParam @Positive int page,
                                                                                           @RequestParam @Positive int size) {
        userService.verifyExistsUser(userId);

        Page<MessageRoomDto.SimpleResponse> responses = messageRoomService.findMessageRoomsByUserId(userId, page, size);    //email

        return new ResponseEntity<>(new MultiResponseDto<>(responses), HttpStatus.OK);
    }

    // 특정 유저의 특정 채팅방 조회
    @GetMapping("/{user-id}/{messageroom-id}")
    public ResponseEntity<MessageRoomDto.Response> getMessages(@PathVariable("user-id") @Positive Long userId,
                                                               @PathVariable("messageroom-id") @Positive Long messageRoomId) {
        User user = userService.verifyExistsUser(userId);   // email

        MessageRoomDto.Response response = messageRoomService.findMessages(user, messageRoomId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 특정 채팅방 삭제
    @DeleteMapping("/{user-id}/{messageroom-id}")
    public ResponseEntity<HttpStatus> deleteMessageRoom(@PathVariable("user-id") @Positive Long userId,
                                                        @PathVariable("messageroom-id") @Positive Long messageRoomId) {
        User user = userService.verifyExistsUser(userId);   // email

        messageRoomService.deleteMessageRoom(user, messageRoomId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
