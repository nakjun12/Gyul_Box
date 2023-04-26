package jeju.oneroom.message.controller;

import jeju.oneroom.common.dto.ListResponseDto;
import jeju.oneroom.common.dto.MultiResponseDto;
import jeju.oneroom.message.dto.MessageDto;
import jeju.oneroom.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    // 메세지 보내기
    @PostMapping
    public ResponseEntity<?> post(@Valid @RequestBody MessageDto.Post postDto) {
        MessageDto.Response createMessage = messageService.createMessage(postDto);
        return new ResponseEntity<>(createMessage, HttpStatus.CREATED);
    }

    // userId로 받은 모든 메시지 리스트 가져오기
    @GetMapping("users/{receiver-id}/messages")
    public ResponseEntity<?> getMessageList(@PathVariable("receiver-id") @Positive long receiverId,
                                            @RequestParam int page,
                                            @RequestParam int size) {
        Page<MessageDto.Response> messageList = messageService.findMessageList(receiverId, page, size);
        return new ResponseEntity<>(new MultiResponseDto<>(messageList), HttpStatus.OK);
    }

    // senderId와 receiverId로 주고 받은 모든 메시지 가져오기
    @GetMapping("users/{receiver-id}/{sender-id}/messages")
    public ResponseEntity<?> gets(@PathVariable("receiver-id") @Positive long receiverId,
                                  @PathVariable("sender-id") @Positive long senderId) {
        List<MessageDto.Response> allMessages = messageService.findAllMessages(receiverId, senderId);
        return new ResponseEntity<>(new ListResponseDto<>(allMessages), HttpStatus.OK);
    }

    // 메세지 삭제
    @DeleteMapping("/{message-id}")
    public ResponseEntity<?> delete(@PathVariable("message-id") @Positive long messageId) {
        messageService.deleteMessage(messageId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
