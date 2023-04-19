package jeju.oneroom.message.controller;

import jeju.oneroom.common.dto.MultiResponseDto;
import jeju.oneroom.message.dto.MessageDto;
import jeju.oneroom.message.mapper.MessageMapper;
import jeju.oneroom.message.repository.MessageRepository;
import jeju.oneroom.user.entity.User;
import jeju.oneroom.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("users/{user-id}/messages")
public class MessageController {

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;


    // 메세지 보내기
    @PostMapping
    public ResponseEntity<?> post(@PathVariable("user-id") @Positive long userId) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping // userId로 받은 모든 메시지 가져오기
    public ResponseEntity<?> gets(@PathVariable("user-id") @Positive long userId,@RequestParam int page,
                                  @RequestParam int size) {
        User findUser = userRepository.findById(userId).orElse(null);
        Page<MessageDto.Response> byReceiver = messageRepository.findByReceiver(findUser, PageRequest.of(page-1,size)).map(m->messageMapper.messageToResponseDto(m));
        return new ResponseEntity<>(new MultiResponseDto<>(byReceiver), HttpStatus.OK);
    }

    @DeleteMapping("/{message-id}")
    public ResponseEntity<?> delete() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
