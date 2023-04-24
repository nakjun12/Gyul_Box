package jeju.oneroom.message.controller;

import jeju.oneroom.common.dto.ListResponseDto;
import jeju.oneroom.common.dto.MultiResponseDto;
import jeju.oneroom.message.dto.MessageDto;
import jeju.oneroom.message.entity.Message;
import jeju.oneroom.message.mapper.MessageMapper;
import jeju.oneroom.message.repository.MessageRepository;
import jeju.oneroom.user.entity.User;
import jeju.oneroom.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;


    // 메세지 보내기
    @PostMapping
    public ResponseEntity<?> post(@PathVariable("user-id") @Positive long userId) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("users/{receiver-id}/messages") // userId로 받은 모든 메시지 리스트 가져오기
    public ResponseEntity<?> getMessageList(@PathVariable("receiver-id") @Positive long receiverId,
                                            @RequestParam int page,
                                            @RequestParam int size) {
        User receiver = userRepository.findById(receiverId).orElse(null);
        Page<MessageDto.Response> messages = messageRepository.findByReceiver(receiver, PageRequest.of(page - 1, size, Sort.by("createdAt").descending()))
                .map(m -> messageMapper.messageToResponseDto(m));
        return new ResponseEntity<>(new MultiResponseDto<>(messages), HttpStatus.OK);
    }

    @GetMapping("users/{receiver-id}/{sender-id}/messages") // userId로 받은 모든 메시지 가져오기
    public ResponseEntity<?> gets(@PathVariable("receiver-id") @Positive long receiverId,
                                  @PathVariable("sender-id") @Positive long senderId) {
        User receiver = userRepository.findById(receiverId).orElse(null);
        User sender = userRepository.findById(senderId).orElse(null);
        List<Message> messages = messageRepository.findByReceiverAndSender(receiver, sender);
        List<MessageDto.Response> result = messages.stream().map(m->messageMapper.messageToResponseDto(m)).collect(Collectors.toList());
        return new ResponseEntity<>(new ListResponseDto<>(result), HttpStatus.OK);
    }

    @DeleteMapping("/{message-id}")
    public ResponseEntity<?> delete() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
